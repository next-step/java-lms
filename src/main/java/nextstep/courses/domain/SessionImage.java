package nextstep.courses.domain;

public class SessionImage {
  private static Long idSequence = 1L;
  private static Double IMAGE_RATIO = 1.5;

  private final Long id;
  private final Integer width;
  private final Integer height;
  private final ImageExtension extension;
  private final Integer size;
  private final String fileName;

  public SessionImage(Integer width, Integer height, String extension, Integer size, String fileName) {
    this.id = idSequence++;
    this.width = width;
    this.height = height;
    this.extension = ImageExtension.of(extension);
    this.size = size;
    this.fileName = fileName;
    this.validate();
  }

  private void validate() {
    if (this.width < 300) {
      throw new IllegalArgumentException("이미지 width는 300px 이상이어야 합니다.");
    }

    if (this.height < 200) {
      throw new IllegalArgumentException("이미지 height는 200px 이상이어야 합니다.");
    }

    if (this.size > 1024) {
      throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
    }

    if (isInValidRatio(width, height)) {
      throw new IllegalArgumentException("이미지 width, height 비율은 3:2여야 합니다.");
    }
  }

  private boolean isInValidRatio(Integer width, Integer height) {
    return !IMAGE_RATIO.equals((double) width / height);
  }
}
