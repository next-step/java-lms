package nextstep.courses.domain;

public class SessionImage {

    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double IMAGE_RATE = 1.5;

    private int width;
    private int height;
    private int capacity;
    private SessionImageType type;

    public SessionImage(int width, int height, int capacity, String type) {
        this(width, height, capacity, new SessionImageType(type));
    }

    public SessionImage(int width, int height, int capacity, SessionImageType jpg) {
        checkImageSize(width, height);
        this.width = width;
        this.height = height;
        this.capacity = capacity;
        this.type = type;
    }

    private void checkImageSize(int width, int height) {
        if (width < MIN_WIDTH)
            throw new IllegalArgumentException("이미지 가로 길이가 300보다 짧습니다.");

        if (height < MIN_HEIGHT)
            throw new IllegalArgumentException("이미지 세로 길이가 200보다 짧습니다.");

        if ((double) width / height != IMAGE_RATE)
            throw new IllegalArgumentException("이미지 가로, 세로 비율이 맞지 않습니다.");
    }
}
