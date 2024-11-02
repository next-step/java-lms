package nextstep.courses.tobe.domain.session;

import nextstep.courses.domain.session.image.ImageFileSize;
import nextstep.courses.domain.session.image.ImageSize;
import nextstep.courses.domain.session.image.ImageType;

import java.time.LocalDateTime;
import java.util.Objects;

public class TobeCoverImage {
    private final long id;
    private final ImageFileSize imageFileSize;
    private final ImageType imageType;
    private final ImageSize imageSize;
    private final long creatorId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TobeCoverImage(int imageFileSize,
                          String imageTypeText,
                          double width,
                          double height,
                          long creatorId) {
        this(0L, new ImageFileSize(imageFileSize), ImageType.toImageType(imageTypeText), new ImageSize(width, height), creatorId, LocalDateTime.now());

    }

    public TobeCoverImage(ImageFileSize imageFileSize,
                          ImageType imageType,
                          ImageSize imageSize,
                          long creatorId) {
        this(0L, imageFileSize, imageType, imageSize, creatorId, LocalDateTime.now());
    }

    public TobeCoverImage(long id,
                          int imageFileSize,
                          String imageTypeText,
                          double width,
                          double height,
                          long creatorId,
                          LocalDateTime createdAt) {
        this(id, new ImageFileSize(imageFileSize), ImageType.toImageType(imageTypeText), new ImageSize(width, height), creatorId, createdAt);
    }

    public TobeCoverImage(long id,
                          ImageFileSize imageFileSize,
                          ImageType imageType,
                          ImageSize imageSize,
                          long creatorId,
                          LocalDateTime createdAt) {
        this.id = id;
        this.imageFileSize = imageFileSize;
        this.imageType = imageType;
        this.imageSize = imageSize;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public ImageFileSize getImageFileSize() {
        return imageFileSize;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public ImageSize getImageSize() {
        return imageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TobeCoverImage that = (TobeCoverImage) o;
        return id == that.id && creatorId == that.creatorId && Objects.equals(imageFileSize, that.imageFileSize) && imageType == that.imageType && Objects.equals(imageSize, that.imageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageFileSize, imageType, imageSize, creatorId);
    }
}
