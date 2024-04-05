package nextstep.courses.domain.image;

public class Image {
    private final int capacity;
    private final Type type;
    private final double width;

    private final double height;
    private final double ratio;

    public Image(int capacity, Type type, int width, int height) {
        if (capacity < 1) {
            throw new IllegalArgumentException("이미지크기는 1MB를 초과할 수 없다");
        }

        if (width < 300) {
            throw new IllegalArgumentException("이미지 width는 300픽셀이상이어야 한다");
        }

        if (height < 200) {
            throw new IllegalArgumentException("이미지 height는 200픽셀이상이어야 한다");
        }

        this.capacity = capacity;
        this.type = type;
        this.width = width;
        this.height = height;
        this.ratio = calculateRatio();
    }

    private double calculateRatio() {
        if (width / height != 1.5) {
            throw new IllegalArgumentException("width와 height의 비율은 3:2여야 한다");
        }
        return width / height;
    }

}
