package nextstep.courses.domain;


public class Image {

  private static final Long MAX_FILE_SIZE = 5 * 1024 * 1024L;

  private final Long id;

  private final String originalFileName;

  private final ImageType imageType;

  private final Url coverImgUrl;

  private final Long fileSize;

  public Image(Long id, String originalFileName, ImageType imageType, Url coverImgUrl, Long fileSize) {
    this.id = id;
    this.originalFileName = originalFileName;
    this.imageType = imageType;
    this.coverImgUrl = coverImgUrl;
    this.fileSize = fileSize;
  }


}
