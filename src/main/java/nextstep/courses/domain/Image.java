package nextstep.courses.domain;


public class Image {

  private final Long id;

  private final String originalFileName;

  private final ImageType imageType;

  private final Url coverImgUrl;


  public Image(Long id, String originalFileName, ImageType imageType, Url coverImgUrl) {
    this.id = id;
    this.originalFileName = originalFileName;
    this.imageType = imageType;
    this.coverImgUrl = coverImgUrl;
  }


}
