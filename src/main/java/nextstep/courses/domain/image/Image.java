package nextstep.courses.domain.image;

public class Image {
    private final int capacity;
    private final Type type;

    public Image(int capacity, Type type) {
        if (capacity > 1) {
            throw new IllegalArgumentException("이미지크기는 1MB를 초과할 수 업다");
        }
        this.capacity = capacity;
        this.type = type;
    }
}
