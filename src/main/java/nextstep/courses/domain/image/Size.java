package nextstep.courses.domain.image;

public class Size {
    private final int value;

    Size(int size) {
        if (size > 1024) throw new IllegalArgumentException("이미지 크기는 1MB(1024Byte) 이하여야 합니다 " + size);

        this.value = size;
    }

    public int getValue() {
        return this.value;
    }
}
