package nextstep.courses.domain.image;

public class Image {
    private final int capacity;
    private final Type type;
    private final int width;

    public Image(int capacity, Type type, int width) {
        if (capacity < 1) {
            throw new IllegalArgumentException("이미지크기는 1MB를 초과할 수 없다");
        }

        if (width < 300) {
            throw new IllegalArgumentException("이미지 width는 300픽셀이상이어야 한다");
        }
        this.capacity = capacity;
        this.type = type;
        this.width = width;
    }
}
