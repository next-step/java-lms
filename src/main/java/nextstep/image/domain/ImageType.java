package nextstep.image.domain;

import java.util.Arrays;
import java.util.Optional;

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

  public static Optional<ImageType> from(String type) {
    return Arrays.stream(values())
        .filter(t -> t.contentType.equalsIgnoreCase(type))
        .findFirst();
  }
}
