package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageType {
  GIF("image/gif"),
  JPEG("image/jpeg"),
  JPG("image/jpg"),
  PNG("image/png"),
  SVG("image/svg+xml");

  private final String contentType;

  ImageType(String contentType) {
    this.contentType = contentType;
  }

  public String contentType() {
    return contentType;
  }

  public static boolean isSupported(String type) {
    return Arrays.stream(values())
        .anyMatch(t -> t.contentType.equalsIgnoreCase(type));
  }
}
