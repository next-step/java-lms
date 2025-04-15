package nextstep.courses.domain.images;

public class ImageDimension {
    private final Double width;
    private final Double height;

    public ImageDimension(Double width, Double height) {
        validate(width, height);
        this.width = width;
        this.height = height;
    }

    private void validate(Double width, Double height) {
        if (width <= 300) {
            throw new IllegalArgumentException("width must be greater than 300");
        }

        if (height <= 200) {
            throw new IllegalArgumentException("height must be greater than 200");
        }

        if (width * 2 != height * 3) {
            throw new IllegalArgumentException("width : height must be 3 : 2");
        }
    }

    @Override
    public String toString() {
        return "ImageDimension{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
