package nextstep.courses.domain.session;

public class CoverImage {

  public static final String IMAGE_TITLE_IS_INCORRECT = "커버 이미지 명이 올바르지 않습니다. 다시 확인해주세요. input: %s";

  private String imageTitle;
  private ImageType imageType;
  private CoverImageDetailInfo coverImageDetailInfo;

  public CoverImage(String imageTitle, ImageType imageType, CoverImageDetailInfo coverImageDetailInfo) {
    valid(imageTitle);
    this.imageTitle = imageTitle;
    this.imageType = imageType;
    this.coverImageDetailInfo = coverImageDetailInfo;
  }

  private void valid(String imageTitle) {
    if (imageTitle == null || imageTitle.isBlank()) {
      throw new IllegalArgumentException(String.format(IMAGE_TITLE_IS_INCORRECT, imageTitle));
    }
  }

  public String getImageTitle() {
    return imageTitle;
  }

  public ImageType getImageType() {
    return imageType;
  }
}
