package nextstep.courses.domain;

import nextstep.courses.exception.ImageSizeOverException;

import java.time.LocalDateTime;
import java.util.Objects;

public class CoverImage extends BaseEntity {
    private static final int MAX_MB_OF_IMAGE_SIZE = 1;

    private final Long id;
    private final Long size;
    private final Extension extension;
    private final ImageDimensions dimensions;

    public CoverImage(long size, String extension, int width, int height) {
        this(1L, size, extension, width, height, LocalDateTime.now(), null);
    }

    public CoverImage(Long id, Long size, String extension, int width, int height, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        validateImageSize(size);
        this.id = id;
        this.size = size;
        this.extension = Extension.from(extension);
        this.dimensions = new ImageDimensions(width, height);
    }

    private void validateImageSize(long size) {
        if (size > MAX_MB_OF_IMAGE_SIZE) {
            throw new ImageSizeOverException(size);
        }
    }

    public Long getId() {
        return id;
    }

    public long getSize() {
        return size;
    }

    public String getExtension() {
        return extension.name();
    }

    public int getWidth() {
        return dimensions.getWidth();
    }

    public int getHeight() {
        return dimensions.getHeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CoverImage image = (CoverImage) o;
        return Objects.equals(size, image.size) && extension == image.extension && Objects.equals(dimensions, image.dimensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), size, extension, dimensions);
    }
}
