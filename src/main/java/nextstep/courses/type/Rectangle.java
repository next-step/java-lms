package nextstep.courses.type;

import java.util.Objects;

/**
 * 직사각형 물체의 크기를 정의합니다.
 * 불변 객체입니다.
 */
public class Rectangle {
    private final int width;
    private final int height;
    private final LengthUnit unit;

    public Rectangle(int width, int height, LengthUnit unit) {
        validateValue(width);
        validateValue(height);

        this.width = width;
        this.height = height;
        this.unit = unit;
    }

    private static void validateValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("이미지 크기를 음수로 지정할 수 없습니다.");
        }
    }

    /**
     * 가로나 세로 둘 중 하나라도 주어진 크기보다 작다면 예외를 던집니다.
     */
    public void throwIfSizeIsSmallerThan(Rectangle size) {
        if (unit != size.unit) {
            throw new IllegalArgumentException("비교하려는 두 대상의 단위가 다릅니다.");
        }

        if (width < size.width) {
            throw new IllegalArgumentException("이미지 가로 크기가 " + size.width + "보다 작습니다.");
        }

        if (height < size.height) {
            throw new IllegalArgumentException("이미지 세로 크기가 " + size.height + "보다 작습니다.");
        }
    }

    public void throwIfRatioIsNotTheSameWith(int widthRatio, int heightRatio) {
        if (!isRatioMatched(width, height, widthRatio, heightRatio)) {
            throw new IllegalArgumentException("이미지의 비가 " + widthRatio + ":" + heightRatio +"가 아닙니다.");
        }
    }

    /**
     * 주어진 4개의 수에 대해 비가 맞는지를 확인합니다.
     * @return a:b = c:d 면 true를 반환합니다.
     */
    private static boolean isRatioMatched(int a, int b, int c, int d) {
        return b*c == a*d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rectangle rectangle = (Rectangle) o;
        return width == rectangle.width && height == rectangle.height && unit == rectangle.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, unit);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width + unit.toString() +
                ", height=" + height + unit.toSymbol() +
                '}';
    }


}