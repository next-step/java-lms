package nextstep.courses.domain.images;

public class ImageDimension {
    private final double width;
    private final double height;

    public ImageDimension(double width, double height) {
        validate(width, height);
        this.width = width;
        this.height = height;
    }

    private void validate(double width, double height) {
        if (width <= 300) {
            throw new InvalidImageDimensionException("width must be greater than 300");
        }

        if (height <= 200) {
            throw new InvalidImageDimensionException("height must be greater than 200");
        }

        if (width * 2 != height * 3) {
            throw new InvalidImageDimensionException("width : height must be 3 : 2");
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
