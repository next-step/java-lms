package nextstep.sessions.domain;

import java.util.Arrays;

public class SessionBody {

  private String title;

  private String contents;

  private byte[] coverImage;

  public SessionBody(String title, String contents, byte[] coverImage) {
    this.title = title;
    this.contents = contents;
    this.coverImage = coverImage;
  }

  public String getTitle() {
    return this.title;
  }

  public String getContents() {
    return this.contents;
  }

  public byte[] getCoverImage() {
    return this.coverImage;
  }

  @Override
  public String toString() {
    return "SessionBody{" +
        "title='" + title + '\'' +
        ", contents='" + contents + '\'' +
        ", coverImage=" + Arrays.toString(coverImage) +
        '}';
  }
}
