package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.users.domain.NsUser;

public class QuestionInfo {

  private String title;

  private String contents;

  private NsUser writer;

  public QuestionInfo(NsUser writer, String title, String contents) {
    this.writer = writer;
    this.title = title;
    this.contents = contents;
  }

  public boolean isOwner(NsUser loginUser) {
    return writer.equals(loginUser);
  }

  @Override
  public String toString() {
    return "title=" + title + ", contents=" + contents + ", writer=" + writer;
  }

  public NsUser getWriter() {
    return writer;
  }
}
