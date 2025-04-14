package nextstep.sessions.domain;

import nextstep.image.domain.Image;

public class SessionInformation {
  private final String title;
  private final Period period;
  private final Image image;

  public SessionInformation(String title, Period period, Image image) {
    this.title = title;
    this.period = period;
    this.image = image;
  }

  public String title() {
    return title;
  }

  public Period period() {
    return period;
  }

  public Image image() {
    return image;
  }
}
