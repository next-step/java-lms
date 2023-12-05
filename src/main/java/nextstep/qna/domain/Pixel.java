package nextstep.qna.domain;

public class Pixel {
    private double size;

    private Pixel(double size) {
        this.size = size;
    }

    public static Pixel of(double size) {
        return new Pixel(size);
    }

    public double getSize() {
        return size;
    }
}
