package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.qna.exception.CannotDeleteExceptionMessage.CAN_DELETE_ONLY_ANSWER_OWNER;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private final Answers answers = new Answers();

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public DeleteHistories delete(NsUser user) throws CannotDeleteException {
        DeleteHistory deleteHistory = deleteQuestion(user);
        List<DeleteHistory> deleteHistories = deleteAnswers(user);
        return DeleteHistories.of(deleteHistory, deleteHistories);
    }

    private DeleteHistory deleteQuestion(NsUser user) throws CannotDeleteException {
        validateDeletePossible(user);
        this.deleted = true;
        return convertToDeleteHistory();
    }

    private void validateDeletePossible(NsUser user) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException(CAN_DELETE_ONLY_ANSWER_OWNER);
        }
    }

    private List<DeleteHistory> deleteAnswers(NsUser user) throws CannotDeleteException {
        return this.answers.delete(user);
    }

    private DeleteHistory convertToDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now());
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
