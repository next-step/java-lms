package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NextStepUser;

import java.time.LocalDateTime;

public class Question {

  private static final String ILLEGAL_OWNER_MESSAGE = "질문을 삭제할 권한이 없습니다.";
  private Long id;

  private String title;

  private String contents;

  private NextStepUser writer;

  private Answers answers = new Answers();

  private boolean deleted = false;

  private LocalDateTime createdDate = LocalDateTime.now();

  private LocalDateTime updatedDate;

  public Question() {
  }

  public Question(NextStepUser writer, String title, String contents) {
    this(0L, writer, title, contents);
  }

  public Question(Long id, NextStepUser writer, String title, String contents) {
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

  public NextStepUser getWriter() {
    return writer;
  }

  public void addAnswer(Answer answer) {
    answer.toQuestion(this);

    answers.addNewAnswer(answer);
  }

  public boolean isOwner(NextStepUser loginUser) {
    return writer.equals(loginUser);
  }

  public boolean isDeleted() {
    return deleted;
  }

  public Question setDeleted(boolean deleted) {
    this.deleted = deleted;
    return this;
  }

  public Answers getAnswers() {
    return answers;
  }

  @Override
  public String toString() {
    return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
  }

  public DeleteHistory delete(NextStepUser loginUser) throws CannotDeleteException {
    validateOwnerQuestion(loginUser);
    deleted = true;

    return new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now());
  }

  public void validateOwnerQuestion(NextStepUser loginUser) throws CannotDeleteException {
    if (!isOwner(loginUser)) {
      throw new CannotDeleteException(ILLEGAL_OWNER_MESSAGE);
    }
  }

  public DeleteHistories deleteQuestion(NextStepUser loginUser) throws CannotDeleteException {
    return DeleteHistories.createDeleteHistories(this, loginUser);
  }
}
