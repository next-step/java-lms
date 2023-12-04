package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

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

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public Question setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        return writeDeleteHistories();
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    private List<DeleteHistory> writeDeleteHistories() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(writeDeleteQuestionHistory());
        deleteHistories.addAll(writeDeleteAnswersHistory());
        return deleteHistories;
    }

    private DeleteHistory writeDeleteQuestionHistory() {
        deleteQuestion();
        return createDeleteQuestionHistory();
    }

    private void deleteQuestion() {
        this.deleted = true;
    }

    private DeleteHistory createDeleteQuestionHistory() {
        return DeleteHistory.createDeleteHistoryByQuestion(this.id, this.writer, LocalDateTime.now());
    }

    private List<DeleteHistory> writeDeleteAnswersHistory() throws CannotDeleteException {
        return answers.writeDeleteAnswersHistory(this.writer);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
