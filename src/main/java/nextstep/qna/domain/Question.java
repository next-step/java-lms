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

  private Answers answers;

  private boolean deleted;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
      this(0L, writer, title, contents, new Answers(), false);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
      this(id, writer, title, contents, new Answers(), false);
    }

  public Question(Long id, NsUser writer, String title, String contents, Answers answers,
      boolean isDeleted) {
    this.id = id;
    this.writer = writer;
    this.title = title;
    this.contents = contents;
    this.answers = answers;
    this.deleted = isDeleted;
    }

  public void add(Answer answer) {
        answer.toQuestion(this);
    answers.create(answer);
    }

  public void deleteBy(NsUser loginUser) throws CannotDeleteException {
    deleteRelatedAnswers(loginUser);
    deleteQuestion();
    }

    public boolean isDeleted() {
      return deleted;
    }

  public Long getId() {
    return this.id;
  }

  public NsUser getWriter() {
    return this.writer;
  }

  private void deleteQuestion() {
    this.deleted = true;
  }

  private void deleteRelatedAnswers(NsUser loginUser) throws CannotDeleteException {
    answers.deleteBy(loginUser);
    }

  public List<DeleteHistory> createDeleteHistories() {
    List<DeleteHistory> deleteHistories = new ArrayList<>();
    deleteHistories.add(new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now()));
    deleteHistories.addAll(answers.createDeleteHistories());
    return deleteHistories;
  }

  @Override
    public String toString() {
    return "Question [id=" + id + ", title=" + title + ", contents=" + contents + ", writer="
        + writer + "]";
    }

}
