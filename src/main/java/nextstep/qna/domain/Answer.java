package nextstep.qna.domain;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer {
    private Long id;

    private NsUser writer;

    private Question question;

    private String contents;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

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
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    public NsUser getWriter() {
        return writer;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    public void delete(NsUser loginUser, List<DeleteHistory> deleteHistories) throws CannotDeleteException {
        if (loginUser != this.writer) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        deleted = true;
        deleteHistories.add(new DeleteHistory(ContentType.ANSWER, this.id, this.writer, LocalDateTime.now()));
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
