package nextstep.courses.domain;

import nextstep.courses.constant.ImageFileType;

public class SessionImage {
    private static final int MAXIMUM_SIZE = 1;
    private static final int MINIMUM_WIDTH = 300;
    private static final int MINIMUM_HEIGHT = 200;
    private static final double IMAGE_RATIO = (double) 3 / 2;

    private final int imageSize;
    private final ImageFileType fileType;
    private final int width;
    private final int height;

    public SessionImage(int imageSize, ImageFileType fileType, int width, int height) {
        this.imageSize = imageSize;
        this.fileType = fileType;
        this.width = width;
        this.height = height;
    }

    public SessionImage(int imageSize, String inputFileType, int width, int height) {
        this(imageSize, ImageFileType.getImageFileType(inputFileType), width, height);
    }

    public void validateSessionImage() {
        if (imageSize > MAXIMUM_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
        }

        if (width < MINIMUM_WIDTH || height < MINIMUM_HEIGHT) {
            throw new IllegalArgumentException("이미지 해상도는 가로 300px, 세로 200px 이상이어야 합니다.");
        }

        if ((double) width / height != IMAGE_RATIO) {
            throw new IllegalArgumentException("이미지의 가로:세로 비율은 3:2 여야 합니다.");
        }
    }
}
