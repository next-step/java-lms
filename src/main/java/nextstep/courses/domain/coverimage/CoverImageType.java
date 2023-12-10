package nextstep.courses.domain.coverimage;

import java.util.Arrays;

public enum CoverImageType {
  GIF("gif")
  , JPG("jpg")
  , JPEG("jpeg")
  , PNG("png")
  , SVG("svg")
  , NO_MATCH("nothing")
  ;

  private final String name;
  private static final CoverImageType[] values = values();

  CoverImageType(String name) {
    this.name = name;
  }

  public static CoverImageType valuesOf(String name) {
    return Arrays.stream(values)
          .filter(it -> it.name.equals(name))
          .findFirst()
          .orElseThrow(() -> new IllegalArgumentException("확장자를 확인해주세요."));
    }
}
