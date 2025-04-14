package nextstep.session.domain;

import java.util.Arrays;
import java.util.List;

public class CoverImage {
    private static final int MAX_FILE_SIZE = 1024 * 1024; // 1MB
    private static final List<String> SUPPORTED_FORMATS_LIST = Arrays.asList(new String[]{"gif", "jpg", "jpeg", "png", "svg"});

    private final String fileName;
    private final String imageFormat;
    private final long fileSize;
    private final int width;
    private final int height;

    public CoverImage(String fileName, String imageFormat, long fileSize, int width, int height) {
        validateFileName(fileName);
        validateFileSize(fileSize);
        validateImageFormat(imageFormat);
        validateImageSize(width, height);

        this.fileName = fileName;
        this.imageFormat = imageFormat;
        this.fileSize = fileSize;
        this.width = width;
        this.height = height;
    }

    private static void validateFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("파일 이름은 공백일 수 없습니다.");
        }
    }

    private static void validateImageFormat(String imageFormat) {
        if (imageFormat == null || !SUPPORTED_FORMATS_LIST.contains(imageFormat.toLowerCase())) {
            throw new IllegalArgumentException("지원되지 않는 이미지 포맷입니다.");
        }
    }

    private static void validateImageSize(int width, int height) {
        if (width < 300 || height < 200) {
            throw new IllegalArgumentException("이미지의 최소 크기는 300x200입니다.");
        }

        double ratio = (double) width / height;
        if (ratio != 1.5) {
            throw new IllegalArgumentException("이미지 비율은 3:2이어야 합니다.");
        }
    }

    private void validateFileSize(long fileSize) {
        if (fileSize == 0) {
            throw new IllegalArgumentException("이미지 크기는 0이 될 수 없습니다.");
        }

        if (fileSize > MAX_FILE_SIZE) { // 1MB
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
        }
    }
}
