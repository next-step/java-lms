package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer {
    private static final String ALREADY_DELETED_MESSAGE = "이미 삭제된 답변입니다.";
    private static final String PERMISSION_DENIED_MESSAGE = "답변을 삭제할 권한이 없습니다.";
    private final LocalDateTime createdDate = LocalDateTime.now();
    private Long id;
    private NsUser writer;
    private Question question;
    private String contents;
    private boolean deleted = false;
    private LocalDateTime updatedDate;

    public Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
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
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Answer setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    public NsUser getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public DeleteHistory delete(NsUser requestUser) throws CannotDeleteException {
        if (isDeleted()) {
            throw new CannotDeleteException(ALREADY_DELETED_MESSAGE);
        }
        if (!isOwner(requestUser)) {
            throw new CannotDeleteException(PERMISSION_DENIED_MESSAGE);
        }
        setDeleted(true);
        return new DeleteHistory(ContentType.ANSWER, id, requestUser, LocalDateTime.now());
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
