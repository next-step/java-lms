package nextstep.session.domain;

import java.util.Arrays;
import java.util.stream.Collectors;
import nextstep.session.InvalidImageConditionsException;

public class SessionCoverImage {

    private final int MIN_WIDTH = 300;
    private final int MIN_HEIGHT = 200;
    private final int MAX_SIZE = 1024 * 1024;

    private final long width;
    private final long height;
    private final long size;
    private final ImageType imageType;

    public SessionCoverImage(long width, long height, long size, String type)
        throws InvalidImageConditionsException {
        validate(width, height, size, type);
        this.width = width;
        this.height = height;
        this.size = size;
        this.imageType = ImageType.valueOf(type.toLowerCase());
    }

    private void validate(long width, long height, long size, String type)
        throws InvalidImageConditionsException {
        if (!validateDimensions(width, height)) {
            throw new InvalidImageConditionsException(
                String.format("이미지는 최소 %d*%d 이상이여야합니다. 입력된 사이즈 %d*%d", MIN_WIDTH, MIN_HEIGHT, width,
                    height));
        }
        if (!validateSize(size)) {
            throw new InvalidImageConditionsException(String.format("이미지 크기는"
                + "%dMB 이하여야합니다. 입력된 이미지 크기 %dMB", MAX_SIZE / (1024 * 1024), size / (1024 * 1024)));
        }
        if (!validateRatio(width, height)) {
            throw new InvalidImageConditionsException("너비와 높이가 3:2 비율이여야 합니다.");
        }

        if (!ImageType.validateType(type)) {
            throw new InvalidImageConditionsException(
                "명시된 확장자만 추가 가능합니다." + Arrays.stream(ImageType.values()).map(Enum::name).collect(
                    Collectors.joining(", ")));
        }

    }

    private boolean validateDimensions(long width, long height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            return false;
        }
        return true;
    }

    private boolean validateSize(long size) {
        if (size > MAX_SIZE) {
            return false;
        }
        return true;
    }

    private boolean validateRatio(long width, long height) {
        if ((width * 2) != (height * 3)) {
            return false;
        }
        return true;
    }

    public ImageType getImageType() {
        return imageType;
    }
}
