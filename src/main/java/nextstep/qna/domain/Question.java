package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private final Answers answers = new Answers();

    private boolean deleted = false;

    private final LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    protected Question() {
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

    public List<DeleteHistory> deleteWrittenBy(NsUser user) throws CannotDeleteException {
        return toHistories(deleteQuestionBy(user), deleteAnswers(user));
    }

    private DeleteHistory deleteQuestionBy(NsUser user) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        this.deleted = true;
        return toHistory();
    }

    private DeleteHistory toHistory() throws CannotDeleteException {
        if (!deleted) {
            throw new CannotDeleteException("답변이 삭제가 되지 않았습니다");
        }
        return new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now());
    }

    private List<DeleteHistory> deleteAnswers(NsUser user) throws CannotDeleteException {
        return this.answers.deleteAnswersBy(user);
    }

    private List<DeleteHistory> toHistories(
            DeleteHistory questionDeleteHistory,
            List<DeleteHistory> answerDeleteHistories)
            throws CannotDeleteException
    {
        if (!deleted) {
            throw new CannotDeleteException("질문이 삭제가 되지 않았습니다");
        }

        List<DeleteHistory> histories = new ArrayList<>();
        histories.add(questionDeleteHistory);
        histories.addAll(answerDeleteHistories);
        return histories;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

}
