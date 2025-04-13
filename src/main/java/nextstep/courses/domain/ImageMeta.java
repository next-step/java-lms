package nextstep.courses.domain;

public class ImageMeta {
  private final String fileName;
  private final ImageType contentType;
  private final long size;

  public ImageMeta(String fileName, ImageType contentType, long size) {
    this.fileName = fileName;
    this.contentType = contentType;
    this.size = size;
  }

  public String fileName() {
    return fileName;
  }

  public ImageType contentType() {
    return contentType;
  }

  public long size() {
    return size;
  }
}
