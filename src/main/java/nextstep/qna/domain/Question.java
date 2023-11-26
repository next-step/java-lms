package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private List<Answer> answerList = new ArrayList<>();

    private Answers answers;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    private Question() {
    }

    private Question(NsUser writer, String title, String contents, Answers answers) {
        this(0L, writer, title, contents);
        this.answers = answers;
    }

    private Question(NsUser writer, String title, String contents, Answers answers, boolean deleted) {
        this(0L, writer, title, contents);
        this.answers = answers;
        this.deleted = deleted;
    }

    public static Question of(NsUser writer, String title, String contents) {
        return new Question(writer, title, contents, Answers.from(new ArrayList<>()));
    }

    public static Question of(NsUser writer, String title, String contents, boolean deleted) {
        return new Question(writer, title, contents, Answers.from(new ArrayList<>()), deleted);
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

    public void addAnswerToAnswerList(Answer answer) {
        //answer.toQuestion(this);
        answerList.add(answer);
    }

    public void addAnswer(Answer answer) {
        //answer.toQuestion(this);
        answers.addAnswer(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void delete() {
        this.deleted = true;
    }

    public void deleteBy(NsUser user) throws CannotDeleteException {
        if(!isOwner(user)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        answers.deleteBy(user);

        delete();
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return isDeleted() == question.isDeleted() && Objects.equals(getId(), question.getId()) && Objects.equals(getTitle(), question.getTitle()) && Objects.equals(getContents(), question.getContents()) && Objects.equals(getWriter(), question.getWriter()) && Objects.equals(getAnswerList(), question.getAnswerList()) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getContents(), getWriter(), getAnswerList(), answers, isDeleted());
    }
}
