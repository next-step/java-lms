package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

  public NsUser getWriter() {
    return writer;
  }

  public void addAnswer(Answer answer) {
    answer.toQuestion(this);
    answers.add(answer);
  }

  public List<DeleteHistory> delete() {
    List<DeleteHistory> deleteHistories = new ArrayList<>();
    deleteHistories.add(makeDeleted());
    deleteHistories.addAll(answers.delete());

    return deleteHistories;
  }

  private DeleteHistory makeDeleted() {
    this.deleted = true;
    return new DeleteHistory(this);
  }

  public boolean checkQuestionOwner(NsUser loginUser) {
    return writer.equals(loginUser);
  }

  public boolean checkAnswersOwner(NsUser loginUser) {
    return answers.checkAnswersOwner(loginUser);
  }

  public boolean isDeleted() {
    return deleted;
  }

  @Override
  public String toString() {
    return "Question [id=" + id + ", title=" + title + ", contents=" + contents + ", writer="
        + writer + "]";
  }
}
