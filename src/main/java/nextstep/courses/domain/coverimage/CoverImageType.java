package nextstep.courses.domain.coverimage;

import java.util.Arrays;

public enum CoverImageType {
  GIF("GIF")
  , JPG("JPG")
  , JPEG("JPEG")
  , PNG("PNG")
  , SVG("SVG")
  , NO_MATCH("NO_MATCH")
  ;

  private final String name;
  private static final CoverImageType[] values = values();

  CoverImageType(String name) {
    this.name = name;
  }

  public static CoverImageType valuesOf(String name) {
    return Arrays.stream(values)
          .filter(it -> it.name.equals(name.toUpperCase()))
          .findFirst()
          .orElseThrow(() -> new IllegalArgumentException("확장자를 확인해주세요."));
  }

  public String getName() {
    return name;
  }
}
