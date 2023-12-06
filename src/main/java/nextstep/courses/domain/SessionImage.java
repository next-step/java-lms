package nextstep.courses.domain;

public class SessionImage {
    /**
     * 이미지 용량, 단위 KB
     */
    private int size;

    /**
     * 이미지 가로 크기, 단위 픽셀
     */
    private int width;

    /**
     * 이미지 세로 크기, 단위 픽셀
     */
    private int height;
    private ImageExtension extension;

    public SessionImage(int size, int width, int height, ImageExtension extension) {
        validateSize(size);
        validateWidthHeight(width, height);

        this.size = size;
        this.width = width;
        this.height = height;
        this.extension = extension;
    }

    private static void validateSize(int size) {
        if (size > 1024) {
            throw new IllegalArgumentException("이미지 용량이 1MB보다 큽니다.");
        }
    }

    private static void validateWidthHeight(int width, int height) {
        if (width < 300) {
            throw new IllegalArgumentException("이미지의 width가 300 미만입니다.");
        }

        if (height < 200) {
            throw new IllegalArgumentException("이미지의 height가 200 미만입니다.");
        }

        if (isRatioMatched(width, height, 3, 2) == false) {
            throw new IllegalArgumentException("이미지의 가로 세로 비가 3:2가 아닙니다.");
        }
    }

    /**
     * 주어진 4개의 수에 대해 비가 맞는지를 확인합니다.
     * @return a:b = c:d 면 true를 반환합니다.
     */
    private static boolean isRatioMatched(int a, int b, int c, int d) {
        return b*c == a*d;
    }
}
