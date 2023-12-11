package nextstep.courses.domain.image;

import nextstep.courses.domain.BaseEntity;

import java.time.LocalDateTime;

public class CoverImage extends BaseEntity {
    private static final Long MAX_SIZE = 1024L * 1024L;
    public static final String MAX_SIZE_OVER_MSG = "이미지 사이즈는 1MB를 초과 할 수 없습니다.";
    private final long size;
    private final ImagePixel imagePixel;
    private final ImageType imageType;

    public CoverImage(final long size, final ImagePixel imagePixel, final ImageType imageType) {
        this(null, size, imagePixel, imageType, LocalDateTime.now(), null);
    }
    public CoverImage(final Long id, final long size, final ImagePixel imagePixel,
                      final ImageType imageType, final LocalDateTime createAt, final LocalDateTime updateAt) {
        super(id, createAt, updateAt);

        checkSize(size);

        this.size = size;
        this.imagePixel = imagePixel;
        this.imageType = imageType;
    }

    private static void checkSize(final long size) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException(MAX_SIZE_OVER_MSG);
        }
    }

    public long size() {
        return size;
    }

    public ImagePixel imagePixel() {
        return imagePixel;
    }

    public ImageType imageType() {
        return imageType;
    }
}
