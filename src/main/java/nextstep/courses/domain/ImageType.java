package nextstep.courses.domain;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 1. 지원하지 않는 확장자 입니다. (확장자 검사)
 */
public enum ImageType {
  JPEG("jpeg"),
  PNG("png"),
  GIF("gif");

  private final String description;

  ImageType(String description) {
    this.description = description;
  }

  public static ImageType of(String extension) {
    return Stream.of(values())
        .filter(imageType -> imageType.isMatchedExtension(extension))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 확장자 입니다."));
  }

  private boolean isMatchedExtension(String extension) {
    return this.description.equals(extension);
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "ImageType{" +
        "description='" + description + '\'' +
        '}';
  }
}
