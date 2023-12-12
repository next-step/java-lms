package nextstep.courses.domain.coverimage;

import java.time.LocalDateTime;
import nextstep.courses.BaseTime;

public class CoverImage extends BaseTime {

  private final long id;
  private final String name;
  private final CoverImageType coverImageType;
  private final ImageFileSize imageFileSize;
  private final ImageSize imageSize;

  private CoverImage(String name, CoverImageType coverImageType, ImageFileSize size, ImageSize imageSize) {
    super();
    this.id = 0L;
    this.name = name;
    this.coverImageType = coverImageType;
    this.imageFileSize = size;
    this.imageSize = imageSize;
  }

  private CoverImage(Long id, String name, CoverImageType coverImageType, ImageFileSize size, ImageSize imageSize, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(createdAt, updatedAt);
    this.id = id;
    this.name = name;
    this.coverImageType = coverImageType;
    this.imageFileSize = size;
    this.imageSize = imageSize;
  }

  public static CoverImage defaultOf(String fileName, long size, long width, long height) {
    String name = fileName(fileName);
    CoverImageType coverImageType = fileImageType(fileName);
    return new CoverImage(name, coverImageType, new ImageFileSize(size),new ImageSize(width, height));
  }

  public static CoverImage defaultOf(Long id, String name, String coverImageType, long size,
      long width, long height, LocalDateTime createdAt, LocalDateTime updatedAt) {
    return new CoverImage(id, name, CoverImageType.valueOf(coverImageType), new ImageFileSize(size),new ImageSize(width, height),
        createdAt, updatedAt);
  }

  public static CoverImage defaultOf(Long id, String name, CoverImageType coverImageType,
      ImageFileSize imageFileSize, ImageSize imageSize, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    return new CoverImage(id, name, coverImageType, imageFileSize,imageSize,
        createdAt, updatedAt);
  }


  private static String fileName(String fileName) {
    int point = fileName.indexOf('.');
    return fileName.substring(0, point);
  }

  private static CoverImageType fileImageType(String fileName) {
    int point = fileName.indexOf('.');
    String imageType = fileName.substring(point + 1);
    return CoverImageType.valuesOf(imageType);
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public CoverImageType getCoverImageType() {
    return coverImageType;
  }

  public ImageFileSize getImageFileSize() {
    return imageFileSize;
  }

  public ImageSize getImageSize() {
    return imageSize;
  }

}
