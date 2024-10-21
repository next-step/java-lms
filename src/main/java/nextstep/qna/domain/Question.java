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

    private Answers answers = new Answers(new ArrayList<>());

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

    //region [checkDeletePermission]
    public void checkDeletePermission(NsUser loginUser) throws CannotDeleteException {
        if (isNotOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    private boolean isNotOwner(NsUser loginUser){
        return !isOwner(loginUser);
    }
    //endregion

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        validate(loginUser);
        markDeleted();
        return getHistory();
    }

    public void markDeleted() {
        this.deleted = true;
    }

    private void validate(NsUser loginUser) throws CannotDeleteException {
        checkDeletePermission(loginUser);
        checkAnswerDeletePermission(loginUser);
    }

    private void checkAnswerDeletePermission(NsUser loginUser) throws CannotDeleteException {
        Answers answers = getAnswers();
        answers.checkDeletePermission(loginUser);
    }

    private List<DeleteHistory> getHistory() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(QuestionDeleteHistory());

        List<DeleteHistory> answerHistories = answers.delete();
        deleteHistories.addAll(answerHistories);
        return deleteHistories;
    }

    private DeleteHistory QuestionDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, this.id, this.writer, LocalDateTime.now());
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Answers getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
