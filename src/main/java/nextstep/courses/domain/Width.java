package nextstep.courses.domain;

public class Width {

    private final int value;

    public Width(int value) {
        if (value < 300) {
            throw new IllegalArgumentException("이미지의 Width 는 300 픽셀 이상이여야 합니다.");
        }
        this.value = value;
    }

    public double getRatioOf(Height height) {
        return height.divide(value);
    }
}
