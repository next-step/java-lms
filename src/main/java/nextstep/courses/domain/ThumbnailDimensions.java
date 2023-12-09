package nextstep.courses.domain;

import nextstep.courses.exception.FileException.FileDimensionsException;

public class ThumbnailDimensions {

    private static final int MIN_THUMBNAIL_FILE_LENGTH = 0;
    private static final int MAX_THUMBNAIL_FILE_WIDTH = 300;
    private static final int MAX_THUMBNAIL_FILE_HEIGHT = 200;
    private static final int WIDTH_RATIO = 2;
    private static final int HEIGHT_RATIO = 3;

    private final int width;
    private final int height;

    public ThumbnailDimensions(int width, int height) {
        validateFileDimensions(width, height);
        this.width = width;
        this.height = height;
    }

    private void validateFileDimensions(int width, int height) {
        validateWidthSize(width);
        validateHeightSize(height);
        validateFileRatio(width, height);
    }

    private static void validateWidthSize(int width) {
        if (width < MIN_THUMBNAIL_FILE_LENGTH) {
            throw new FileDimensionsException("파일 너비는 음수일 수 없습니다.");
        }
        if (width < MAX_THUMBNAIL_FILE_WIDTH) {
            throw new FileDimensionsException("파일 너비는 300픽셀 이상이어야합니다.");
        }
    }

    private static void validateHeightSize(int height) {
        if (height < MIN_THUMBNAIL_FILE_LENGTH) {
            throw new FileDimensionsException("파일 높이는 음수일 수 없습니다.");
        }
        if (height < MAX_THUMBNAIL_FILE_HEIGHT) {
            throw new FileDimensionsException("파일 높이는 200픽셀 이상이어야합니다.");
        }
    }

    private static void validateFileRatio(int width, int height) {
        if (width * WIDTH_RATIO != height * HEIGHT_RATIO) {
            throw new FileDimensionsException("파일 너비와 높이는 3:2여야합니다.");
        }
    }
}
