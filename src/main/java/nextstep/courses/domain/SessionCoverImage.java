package nextstep.courses.domain;

import java.util.Set;
import nextstep.courses.InvalidCoverImageException;

public class SessionCoverImage {

    public static final int MIN_WIDTH = 300;
    public static final int MIN_HEIGHT = 200;
    public static final double WIDTH_RATIO = 3.0;
    public static final double HEIGHT_RATIO = 2.0;
    public static Set<String> ALLOWED_EXTS = Set.of("gif", "jpg", "jpeg", "png", "svg");
    public static final long MAX_BYTE_SIZE = 1024 * 1024;

    private static final double PRECISION = 0.0001;

    private Long id;
    private Long fileByteSize;
    private String ext;
    private int width;
    private int height;
    private String url;

    public SessionCoverImage(Long fileByteSize, String ext, int width, int height, String url) {
        this(0L, fileByteSize, ext, width, height, url);
    }

    public SessionCoverImage(Long id, Long fileByteSize, String ext, int width, int height,
        String url) {
        this.id = id;

        validateFileSize(fileByteSize);
        this.fileByteSize = fileByteSize;

        validateExt(ext);
        this.ext = ext;

        validateSize(width, height);
        this.width = width;
        this.height = height;
        this.url = url;
    }
    private void validateFileSize(long fileByteSize) {
        if (fileByteSize > MAX_BYTE_SIZE) {
            throw new InvalidCoverImageException("강의 커버 이미지 파일 용량이 너무 큽니다");
        }
    }

    private void validateExt(String ext) {
        boolean notValidExt = !ALLOWED_EXTS.contains(ext);
        if (notValidExt) {
            throw new InvalidCoverImageException("허용되지 않는 강의 커버 이미지 확장자입니다");
        }
    }

    private void validateSize(int width, int height) {
        validateMinSize(width, height);
        validateRatio(width, height);
    }

    private static void validateRatio(int width, int height) {
        boolean isValidRatio = Math.abs((width / WIDTH_RATIO) - (height / HEIGHT_RATIO)) < PRECISION;
        if (!isValidRatio) {
            throw new InvalidCoverImageException("강의 커버 이미지 비율이 유효하지 않습니다");
        }
    }

    private static void validateMinSize(int width, int height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new InvalidCoverImageException("강의 커버 이미지 가로 또는 세로 크기가 너무 작습니다");
        }
    }

    public Long getId() {
        return id;
    }
}
