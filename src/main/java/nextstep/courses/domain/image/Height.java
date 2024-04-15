package nextstep.courses.domain.image;

public class Height {
    private static final int MAX = 200;
    private final int value;

    Height(int height) {
        if (height < MAX) throw new IllegalArgumentException("height는 200픽셀 이상이어야 합니다 " + height);

        this.value = height;
    }

    public int getValue() {
        return this.value;
    }
}
