package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
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

    private Answers answers = new Answers();

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question() {
    }

    public Question(NsUser writer,
                    String title,
                    String contents) {
        this(0L, writer, title, contents);
    }

    public Question(NsUser writer,
                    String title,
                    String contents,
                    LocalDateTime localDateTime) {
        this(0L, writer, title, contents);
        this.createdDate = localDateTime;
    }

    public Question(NsUser writer,
                    String title,
                    String contents,
                    boolean deleted,
                    LocalDateTime localDateTime) {
        this(0L, writer, title, contents);
        this.deleted = deleted;
        this.createdDate = localDateTime;
    }

    public Question(Long id,
                    NsUser writer,
                    String title,
                    String contents) {
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

    public List<DeleteHistory> delete(NsUser loginUser,
                                      LocalDataTimeHolder localDataTimeHolder) {
        validateOwner(loginUser);

        this.deleted = true;

        ArrayList<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, id, writer, localDataTimeHolder.now()));
        deleteHistories.addAll(answers.delete(loginUser, localDataTimeHolder));

        return deleteHistories;
    }

    private void validateOwner(NsUser loginUser) {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Answer> getAnswers() {
        return answers.getAll();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return deleted == question.deleted && Objects.equals(id, question.id) && Objects.equals(title, question.title) && Objects.equals(contents, question.contents) && Objects.equals(writer, question.writer) && Objects.equals(answers, question.answers) && Objects.equals(createdDate, question.createdDate) && Objects.equals(updatedDate, question.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, contents, writer, answers, deleted, createdDate, updatedDate);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

}
