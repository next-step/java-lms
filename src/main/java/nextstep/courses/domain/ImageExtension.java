package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageExtension {
  GIF("gif"), JPG("jpg"), JPEG("jgeg"), PNG("png"), SVG("svg");

  private final String value;

  ImageExtension(final String value) {
    this.value = value.toLowerCase();
  }

  public static ImageExtension of(final String value) {
    return Arrays.stream(values())
            .filter(extension -> extension.value.equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 이미지 확장자입니다."));
  }
}
