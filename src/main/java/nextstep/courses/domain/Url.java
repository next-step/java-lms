package nextstep.courses.domain;

public class Url {

  private final String url;

  public Url(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return "Url{" +
        "url='" + url + '\'' +
        '}';
  }
}
