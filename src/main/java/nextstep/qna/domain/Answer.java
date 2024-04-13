package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

import static nextstep.qna.ExceptionMessage.NO_AUTHORITY_TO_DELETE_ANSWER;

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

    public DeleteHistory deleteByUser(NsUser user) throws CannotDeleteException {
        validateDeletableAnswer(user);
        updateDeletedAsTrue();
        return deleteHistoryOfAnswer();
    }

    private void validateDeletableAnswer(NsUser user) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException(NO_AUTHORITY_TO_DELETE_ANSWER.message());
        }
    }

    private void updateDeletedAsTrue() {
        this.deleted = true;
    }

    private DeleteHistory deleteHistoryOfAnswer() {
        return DeleteHistory.answerOf(id, writer);
    }

    public boolean isOwner(NsUser user) {
        return writer.equals(user);
    }

    public boolean isDeleted() {
        return deleted;
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

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
