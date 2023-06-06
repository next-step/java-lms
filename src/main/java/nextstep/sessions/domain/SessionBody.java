package nextstep.sessions.domain;

public class SessionBody {

  private String title;

  private String contents;

  private byte[] coverImage;

  public SessionBody(String title, String contents, byte[] coverImage) {
    this.title = title;
    this.contents = contents;
    this.coverImage = coverImage;
  }
}
