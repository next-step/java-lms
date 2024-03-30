package nextstep.qna.domain;

import java.time.LocalDateTime;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answer {

    private Long id;

    private NsUser writer;

    private Question question;

    private String contents;

    private boolean deleted = false;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private CreatedDateTimeProvider createdDateTimeProvider;

    public Answer() {
    }

    public Answer(CreatedDateTimeProvider createdDateTimeProvider, NsUser writer, Question question, String contents) {
        this(createdDateTimeProvider, null, writer, question, contents);
    }

    public Answer(CreatedDateTimeProvider createdDateTimeProvider, Long id, NsUser writer, Question question, String contents) {
        this.id = id;
        if (writer == null) {
            throw new UnAuthorizedException();
        }

        if (question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
        this.createdDateTimeProvider = createdDateTimeProvider;
        this.createdDate = createdDateTimeProvider.now();
    }

    public DeleteHistory delete(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        deleted = true;
        return new DeleteHistory(ContentType.ANSWER, getId(), getWriter(), createdDateTimeProvider.now());
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

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
