package nextstep.courses.domain.images;

public class ImageSizeKb {
    private double value;

    public ImageSizeKb(double value) {
        validate(value);
        this.value = value;
    }

    private void validate(double value) {
        if (value <= 0) {
            throw new InvalidImageSizeException("Image size must be greater than 0");
        }

        if (value > 1000) {
            throw new InvalidImageSizeException("Image size must be less than or equal to 1000");
        }
    }
}
