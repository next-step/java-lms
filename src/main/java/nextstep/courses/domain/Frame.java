package nextstep.courses.domain;

public class Frame {

    private final Width width;
    private final Height height;

    public Frame(int width, int height) {
        this(new Width(width), new Height(height));
    }

    public Frame(Width width, Height height) {
        if (width.getRatioOf(height) != 1.5) {
            throw new IllegalArgumentException("이미지의 width와 height의 비율은 3:2여야 합니다.");
        }
        this.width = width;
        this.height = height;
    }
}
