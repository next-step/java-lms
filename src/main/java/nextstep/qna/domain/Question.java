package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.qna.exception.CannotDeleteExceptionMessage;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

import static nextstep.qna.exception.CannotDeleteExceptionMessage.*;

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

    public Question delete(NsUser user) throws CannotDeleteException {
        deleteQuestion(user);
        deleteAnswers(user);
        return this;
    }

    public DeleteHistories addDeleteHistory() {
        DeleteHistories histories = new DeleteHistories();
        this.addTo(histories);
        this.answers.addTo(histories);
        return histories;
    }

    private void deleteQuestion(NsUser user) throws CannotDeleteException {
        validateDeletePossible(user);
        this.deleted = true;
    }

    private void validateDeletePossible(NsUser user) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException(CAN_DELETE_ONLY_ANSWER_OWNER);
        }
    }

    private void deleteAnswers(NsUser user) throws CannotDeleteException {
        this.answers.delete(user);
    }

    private void addTo(DeleteHistories deleteHistories) {
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now()));
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
