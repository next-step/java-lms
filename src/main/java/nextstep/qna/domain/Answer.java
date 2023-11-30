package nextstep.qna.domain;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

import static nextstep.qna.domain.ContentType.*;

public class Answer {
    private Long id;

    private NsUser writer;

    private Question question;

    private String contents;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    private LocalDateTime deletedDate;

    public Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        this.id = id;
        if(writer == null) {
            throw new UnAuthorizedException();
        }

        if(question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.question.addAnswer(this);
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public NsUser getWriter() {
        return writer;
    }

    public boolean isSameWriter(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public void delete(LocalDateTime now) {
        this.deleted = true;
        this.deletedDate = now;
    }

    public DeleteHistory createDeleteHistory() {
        if (deleted) {
            return new DeleteHistory(ANSWER, id, writer, deletedDate);
        }

        throw new IllegalArgumentException("해당 답변은 삭제되지 않았습니다. 답변 ID ::" + id);
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
