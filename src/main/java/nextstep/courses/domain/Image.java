package nextstep.courses.domain;

public class Image {
    public Image(int capacity) {
        if (capacity > 1) {
            throw new IllegalArgumentException("이미지크기는 1MB를 초과할 수 업다");
        }
    }
}
