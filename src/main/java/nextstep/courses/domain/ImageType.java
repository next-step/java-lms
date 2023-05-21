package nextstep.courses.domain;

public enum ImageType {
  JPEG("jpeg"),
  PNG("png"),
  GIF("gif");

  private final String description;

  ImageType(String description) {
    this.description = description;
  }

}
