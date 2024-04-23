package nextstep.courses.domain.image;

public class Height {

    private final int value;

    public Height(int value) {
        if (value < 200) {
            throw new IllegalArgumentException("이미지의 Height 는 200 픽셀 이상이여야 합니다.");
        }
        this.value = value;
    }

    public double divide(double value) {
        return value/this.value;
    }
}
