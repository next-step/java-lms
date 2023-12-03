package nextstep.courses.domain;

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

  CoverImageType(String name) {
    this.name = name;
  }

  public static CoverImageType valuesOf(String name) {
    return Arrays.stream(values())
          .filter(it -> it.name.equals(name))
          .findFirst()
          .orElseGet(() -> CoverImageType.NO_MATCH);
    }
}
