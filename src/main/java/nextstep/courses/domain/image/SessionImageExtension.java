package nextstep.courses.domain.image;

import java.util.ArrayList;
import java.util.List;

public enum SessionImageExtension {
  GIF("gif"),
  JPG("jpg", "jpeg"),
  PNG("png"),
  SVG("svg");

  private List<String> names;

  SessionImageExtension(String... names){
    this.names = new ArrayList<>(List.of(names));
  }

  public static SessionImageExtension from(String extension) {
    String lowerExt = extension.toLowerCase();
    for (SessionImageExtension type : values()) {
      if (type.names.contains(lowerExt)) {
        return type;
      }
    }
    throw new IllegalArgumentException("지원하지 않는 이미지 확장자입니다: " + extension);
  }
}
