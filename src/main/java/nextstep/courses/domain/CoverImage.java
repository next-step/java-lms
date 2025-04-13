package nextstep.courses.domain;

import nextstep.courses.InvalidCoverImageException;

public class CoverImage {

    private final int width;
    private final int height;
    private final int  sizeInBytes;
    private final ImageExtension extension;

    public CoverImage(int width, int height, int sizeInBytes, ImageExtension extension) {
        this.width = width;
        this.height = height;
        this.sizeInBytes = sizeInBytes;
        this.extension = extension;
        validate();
    }

    private void validate() {
        if (sizeInBytes > 1_000_000) {
            throw new InvalidCoverImageException("1MB를 초과하는 이미지입니다.");
        }
        if (width < 300) {
            throw new InvalidCoverImageException("너비는 300px 이상이어야 합니다.");
        }
        if (height < 200) {
            throw new InvalidCoverImageException("높이는 200px 이상이어야 합니다.");
        }
        if (width * 2 != height * 3) {
            throw new InvalidCoverImageException("비율은 3:2여야 합니다.");
        }
        if (extension == null) {
            throw new InvalidCoverImageException("지원하지 않는 확장자입니다.");
        }
    }

}
