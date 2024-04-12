package nextstep.courses.domain;

public class SessionImage {
  private static final Double IMAGE_RATIO = 1.5;

  private Long id;
  private final Integer width;
  private final Integer height;
  private final ImageExtension extension;
  private final Integer fileSize;
  private final String fileName;
  private final Long sessionId;

  public SessionImage(Integer width, Integer height, String extension, Integer fileSize, String fileName, Long sessionId) {
    this(0L, width, height, extension, fileSize, fileName, sessionId);
  }

  public SessionImage(Long id, Integer width, Integer height, String extension, Integer fileSize, String fileName, Long sessionId) {
    this.id = id;
    this.width = width;
    this.height = height;
    this.extension = ImageExtension.of(extension);
    this.fileSize = fileSize;
    this.fileName = fileName;
    this.sessionId = sessionId;
    this.validate();
  }

  private void validate() {
    if (this.width < 300) {
      throw new IllegalArgumentException("이미지 width는 300px 이상이어야 합니다.");
    }

    if (this.height < 200) {
      throw new IllegalArgumentException("이미지 height는 200px 이상이어야 합니다.");
    }

    if (this.fileSize > 1024) {
      throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
    }

    if (isInValidRatio(width, height)) {
      throw new IllegalArgumentException("이미지 width, height 비율은 3:2여야 합니다.");
    }
  }

  private boolean isInValidRatio(Integer width, Integer height) {
    return !IMAGE_RATIO.equals((double) width / height);
  }

  public Long getId() {
    return this.id;
  }

  public Integer getWidth() {
    return this.width;
  }

  public Integer getHeight() {
    return this.height;
  }

  public ImageExtension getExtension() {
    return this.extension;
  }

  public Integer getFileSize() {
    return this.fileSize;
  }

  public String getFileName() {
    return this.fileName;
  }

  public Long getSessionId() {
    return this.sessionId;
  }
}
