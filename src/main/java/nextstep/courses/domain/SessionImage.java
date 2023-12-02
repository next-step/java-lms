package nextstep.courses.domain;

import nextstep.courses.enumeration.ExtensionType;
import nextstep.courses.exception.ExceedImageRatioException;
import nextstep.courses.exception.ExceedImageSizeException;
import nextstep.courses.exception.ExceedImageWidthHeightException;

import static nextstep.courses.constants.ImageSize.*;

public class SessionImage extends BaseEntity {

    private final Long id;
    private final String imageUrl;
    private final ExtensionType extensionType;
    private final Long size;

    public SessionImage(Long id, String imageUrl, ExtensionType extensionType, Long size, Long width, Long height) {
        super();

        validate(size, width, height);
        this.id = id;
        this.imageUrl = imageUrl;
        this.extensionType = extensionType;
        this.size = size;
    }

    public static SessionImage of(Long id, String imageUrl, String extensionType, Long size, Long width, Long height) {
        return new SessionImage(id, imageUrl, ExtensionType.findType(extensionType), size, width, height);
    }

    private void validate(long size, long width, long height) {
        if (size > ONE_MB) {
            throw new ExceedImageSizeException("이미지 사이즈는 최대 1MB 입니다. 현재사이즈 : " + size);
        }

        if (width < MINIMUM_WIDTH || height < MINIMUM_HEIGHT) {
            throw new ExceedImageWidthHeightException("이미지 최소 넓이는 " + MINIMUM_WIDTH + "px, 높이는 최소 " + MINIMUM_HEIGHT + "입니다.");
        }

        if (!validRatio(width, height)) {
            throw new ExceedImageRatioException("이미지 넓이와 높이의 비율은 " + WIDTH_RATIO + " : " + HEIGHT_RATIO + "입니다.");
        }
    }

    private boolean validRatio(long width, long height) {
        return width * HEIGHT_RATIO == height * WIDTH_RATIO;
    }

    public ExtensionType getExtensionType() {
        return this.extensionType;
    }

    public long getSize() {
        return this.size;
    }

    public String getUrl() {
        return this.imageUrl;
    }
}
