package nextstep.courses.domain;

public class Image {
  private static final long MAX_SIZE_IN_BYTES = 1_048_576; // 1MB

  private final ImageMeta meta;
  private final Dimension dimension;

  public Image(ImageMeta meta, Dimension dimension) {
    validate(meta, dimension);
    this.meta = meta;
    this.dimension = dimension;
  }

  private void validate(ImageMeta meta, Dimension dimension) {
    if (meta.size() > MAX_SIZE_IN_BYTES) {
      throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
    }
    if (!dimension.isValid()) {
      throw new IllegalArgumentException("이미지 비율 또는 크기가 유효하지 않습니다.");
    }
  }

  public ImageMeta meta() {
    return meta;
  }

  public Dimension dimension() {
    return dimension;
  }
}
