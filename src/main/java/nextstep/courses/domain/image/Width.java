package nextstep.courses.domain.image;

public class Width {
    private static final int MAX = 300;
    private final int value;

    Width(int width) {
        if (width < MAX) throw new IllegalArgumentException("width는 300픽셀 이상이어야 합니다 " + width);

        this.value = width;
    }

    public int getValue() {
        return this.value;
    }
}
