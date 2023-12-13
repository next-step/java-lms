package nextstep.courses.domain.coverimage;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum CoverImageType {
  GIF("GIF")
  , JPG("JPG")
  , JPEG("JPEG")
  , PNG("PNG")
  , SVG("SVG")
  ;

  private final String name;
  private static final CoverImageType[] values = values();
  private static final String valuesString = Arrays.stream(values)
                                                    .map(String::valueOf)
                                                    .collect(Collectors.joining(", "));

  CoverImageType(String name) {
    this.name = name;
  }

  public static CoverImageType valuesOf(String name) {
    return Arrays.stream(values)
                 .filter(it -> it.name.equals(name.toUpperCase()))
                 .findFirst()
                 .orElseThrow(() -> new IllegalArgumentException("확장자는 " + valuesString + " 만 가능합니다." ))
        ;
  }

  public String getName() {
    return name;
  }
}
