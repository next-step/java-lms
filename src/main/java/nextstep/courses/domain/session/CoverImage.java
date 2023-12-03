package nextstep.courses.domain.session;

import org.springframework.util.Assert;

import java.util.Objects;

public class CoverImage {
    public static final int FILE_SIZE_LIMIT_1MB = 1024 * 1024;
    private final Long id;
    private final int fileSize;
    private final ImageType imageType;
    private final int width;
    private final int height;

    public CoverImage(final int fileSize, final ImageType imageType, final int width, final int height) {
        this(0L, fileSize, imageType, width, height);
    }

    public CoverImage(final Long id, final int fileSize, final ImageType imageType, final int width, final int height) {
        validate(fileSize, imageType, width, height);

        this.id = id;
        this.fileSize = fileSize;
        this.imageType = imageType;
        this.width = width;
        this.height = height;
    }

    public static CoverImage defaultCoverImage() {
        return new CoverImage(1000, ImageType.JPG, 300, 200);
    }

    private void validate(final int fileSize, final ImageType imageType, final int width, final int height) {
        Assert.isTrue(fileSize <= FILE_SIZE_LIMIT_1MB, "fileSize must be less than 1MB");
        Assert.notNull(imageType, "imageType must not be null");
        Assert.isTrue(width >= 300, "width must be greater than and equal to 300 pixel");
        Assert.isTrue(height >= 200, "height must be greater than and equal to 200 pixel");

        Assert.isTrue((double) width / height == 1.5, "ratio must be 1.5");
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CoverImage that = (CoverImage) o;
        return fileSize == that.fileSize && width == that.width && height == that.height && imageType == that.imageType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileSize, imageType, width, height);
    }
}
