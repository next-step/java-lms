package nextstep.courses.domain;

import nextstep.courses.domain.session.image.ImageFileSize;
import nextstep.courses.domain.session.image.ImageSize;
import nextstep.courses.domain.session.image.ImageType;

import java.time.LocalDateTime;
import java.util.Objects;

public class CoverImage {
    private final long id;
    private final long sessionId;
    private final ImageFileSize imageFileSize;
    private final ImageType imageType;
    private final ImageSize imageSize;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CoverImage(long sessionId,
                      int imageFileSize,
                      String imageTypeText,
                      double width,
                      double height) {
        this(0L, sessionId, new ImageFileSize(imageFileSize), ImageType.toImageType(imageTypeText), new ImageSize(width, height), LocalDateTime.now(), LocalDateTime.now());
    }

    public CoverImage(long sessionId,
                      ImageFileSize imageFileSize,
                      ImageType imageType,
                      ImageSize imageSize) {
        this(0L, sessionId, imageFileSize, imageType, imageSize, LocalDateTime.now(), LocalDateTime.now());
    }

    public CoverImage(long id,
                      long sessionId,
                      int imageFileSize,
                      String imageTypeText,
                      double width,
                      double height,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt) {
        this(id, sessionId, new ImageFileSize(imageFileSize), ImageType.toImageType(imageTypeText), new ImageSize(width, height), createdAt, updatedAt);
    }

    public CoverImage(long id,
                      long sessionId,
                      ImageFileSize imageFileSize,
                      ImageType imageType,
                      ImageSize imageSize,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.imageFileSize = imageFileSize;
        this.imageType = imageType;
        this.imageSize = imageSize;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public long getSessionId() {
        return sessionId;
    }

    public ImageFileSize getImageFileSize() {
        return imageFileSize;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public ImageSize getImageSize() {
        return imageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverImage that = (CoverImage) o;
        return id == that.id && Objects.equals(imageFileSize, that.imageFileSize) && imageType == that.imageType && Objects.equals(imageSize, that.imageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageFileSize, imageType, imageSize);
    }

}
