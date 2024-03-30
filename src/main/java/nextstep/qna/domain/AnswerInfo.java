package nextstep.qna.domain;

import java.time.LocalDateTime;
import nextstep.users.domain.NsUser;

public class AnswerInfo {
  private NsUser writer;

  private String contents;

  private LocalDateTime createdDate = LocalDateTime.now();

  private LocalDateTime updatedDate;

  public AnswerInfo(NsUser writer, String contents) {
    this.writer = writer;
    this.contents = contents;
  }

  public boolean isOwner(NsUser writer) {
    return this.writer.equals(writer);
  }

  public NsUser getWriter() {
    return writer;
  }
  @Override
  public String toString() {
    return "writer =" + writer + ", contents = " +  contents;
  }
}
