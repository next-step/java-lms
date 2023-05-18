package nextstep.qna.domain;

import java.time.LocalDateTime;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answer {

  private Long id;

  private NsUser writer;

  private Question question;

  private String contents;

  private boolean deleted = false;

  private LocalDateTime createdDate = LocalDateTime.now();

  private LocalDateTime updatedDate;

  public Answer() {
  }

  public Answer(NsUser writer, Question question, String contents) {
    this(null, writer, question, contents);
  }

  public Answer(Long id, NsUser writer, Question question, String contents) {
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

  public NsUser getWriter() {
    return writer;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void makeDeleted(DeleteHistories deleteHistories) {
    this.deleted = true;
    deleteHistories.addDeleteHistory(new DeleteHistory(this));
  }

  public boolean isOwner(NsUser writer) {
    return this.writer.equals(writer);
  }

  public void toQuestion(Question question) {
    this.question = question;
  }

  @Override
  public String toString() {
    return "Answer [id=" + id + ", writer=" + writer + ", contents=" + contents + "]";
  }
}
