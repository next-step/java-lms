package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NextStepUser;

import java.time.LocalDateTime;

public class Answer {

  private static final String ILLEGAL_OWNER_MESSAGE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";
  private Long id;

  private NextStepUser writer;

  private Question question;

  private String contents;

  private boolean deleted = false;

  private LocalDateTime createdDate = LocalDateTime.now();

  private LocalDateTime updatedDate;

  public Answer() {
  }

  public Answer(NextStepUser writer, Question question, String contents) {
    this(null, writer, question, contents);
  }

  public Answer(Long id, NextStepUser writer, Question question, String contents) {
    this.id = id;
    if (writer == null) {
      throw new UnAuthorizedException();
    }

    if (question == null) {
      throw new NotFoundException();
    }

    this.writer = writer;
    this.question = question;
    this.contents = contents;
  }

  public Long getId() {
    return id;
  }

  public boolean isDeleted() {
    return deleted;
  }

  private boolean isOwner(NextStepUser writer) {
    return this.writer.equals(writer);
  }

  public NextStepUser getWriter() {
    return writer;
  }

  public void toQuestion(Question question) {
    this.question = question;
  }

  public void validateIsOwner(NextStepUser loginUser) throws CannotDeleteException {
    if (!isOwner(loginUser)) {
      throw new CannotDeleteException(ILLEGAL_OWNER_MESSAGE);
    }
  }

  public DeleteHistory delete(NextStepUser loginUser) throws CannotDeleteException {
    validateIsOwner(loginUser);
    deleted = true;

    return new DeleteHistory(ContentType.ANSWER, id, writer, LocalDateTime.now());
  }

  @Override
  public String toString() {
    return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
  }
}
