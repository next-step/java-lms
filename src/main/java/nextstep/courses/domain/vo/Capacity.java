package nextstep.courses.domain.vo;

public class Capacity {

    private int imageSize;

    public Capacity(int size) {
        this.imageSize = size;
    }

    public boolean satisfy(int size) {
        return this.imageSize <= size;
    }
}
