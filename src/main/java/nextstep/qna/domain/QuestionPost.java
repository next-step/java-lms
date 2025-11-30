package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class QuestionPost {

  private String title;

  private String contents;

  private NsUser writer;

  public QuestionPost(NsUser writer, String title, String contents) {
    this.title = title;
    this.contents = contents;
    this.writer = writer;
  }

  public NsUser getWriter() {
    return this.writer;
  }

  public boolean isOwner(NsUser user) {
    return writer.equals(user);
  }


  public String getTitle() {
    return this.title;
  }

  public String getContents() {
    return this.contents;
  }
}
