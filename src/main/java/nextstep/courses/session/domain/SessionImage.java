package nextstep.courses.session.domain;

import static org.springframework.util.StringUtils.hasText;

public class SessionImage {
    public static final long MB = 1024 * 1024;
    public static final int MIN_WIDTH = 300;
    public static final int MIN_HEIGHT = 200;
    private static final String EXTENSION_REGEX = ".*\\.(jpg|jpeg|png|gif|svg)$";;

    private String url;
    private String name;
    private long size;
    private int width;
    private int height;


    public SessionImage(String url, long size, String name, int width, int height) {
        if (!hasText(url)) {
            throw new IllegalArgumentException("이미지 주소값은 필수 입니다");
        }

        if (size <= 0 || size > MB) {
            throw new IllegalArgumentException("강의 이미지 크기는 1MB 이하여야 합니다");
        }

        if (!hasText(name) || !name.matches(EXTENSION_REGEX)) {
            throw new IllegalArgumentException("강의 이미지 크기는 1MB 이하여야 합니다");
        }

        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException("강의 이미지 너비는 300, 높이는 200 픽셀 이상여야 합니다");
        }

        if (isRatioThreeToTwo(width, height)) {
            throw new IllegalArgumentException("강의 이미지 너비, 높이 비율은 3:2 여야 합니다");
        }

        this.url = url;
        this.size = size;
        this.name = name;
        this.width = width;
        this.height = height;
    }


    private boolean isRatioThreeToTwo(int width, int height) {
        if (width <= 0 || height <= 0) {
            return false;
        }

        return width * 2 == height * 3;
    }
}
