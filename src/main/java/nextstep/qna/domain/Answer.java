package nextstep.qna.domain;

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


    public boolean deleted(NsUser writer) throws CannotDeleteException {
        if(isNotOwner(writer)) {
            throw new CannotDeleteException("답변을 삭제할 권한이 없습니다.");
        }
        this.deleted = true;
        return true;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isNotOwner(NsUser writer) {
        return !this.writer.equals(writer);
    }

    public NsUser getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    public DeleteHistory toDeleteHistory(LocalDateTime deletionTime) {
        return new DeleteHistory(ContentType.ANSWER, id, writer, deletionTime);
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
