package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static nextstep.qna.ExceptionMessage.DIFFERENT_WRITER_OF_QUESTION_AND_WRITER_OF_ANSWER;
import static nextstep.qna.ExceptionMessage.NO_AUTHORITY_TO_DELETE_ANSWER;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers = new Answers();

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

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public List<DeleteHistory> deleteByUser(NsUser user) throws CannotDeleteException {
        validateDeletableQuestion(user);
        List<DeleteHistory> deleteHistoriesOfAnswers = answers.deleteByUser(user);
        updateDeletedAsTrue();
        return deleteHistoriesOfQuestion(deleteHistoriesOfAnswers);
    }

    private void validateDeletableQuestion(NsUser user) throws CannotDeleteException {
        validateUser(user);
        validateAnswers();
    }

    private void validateUser(NsUser user) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException(NO_AUTHORITY_TO_DELETE_ANSWER.message());
        }
    }

    private boolean isOwner(NsUser user) {
        return writer.equals(user);
    }

    private void validateAnswers() throws CannotDeleteException {
        if (!answers.isDeletableByWriter(writer)) {
            throw new CannotDeleteException(DIFFERENT_WRITER_OF_QUESTION_AND_WRITER_OF_ANSWER.message());
        }
    }

    private void updateDeletedAsTrue() {
        this.deleted = true;
    }

    private List<DeleteHistory> deleteHistoriesOfQuestion(List<DeleteHistory> deleteHistoriesOfAnswers) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories.add(DeleteHistory.questionDeleteHistory(id, writer));
        deleteHistories.addAll(deleteHistoriesOfAnswers);

        return deleteHistories;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
