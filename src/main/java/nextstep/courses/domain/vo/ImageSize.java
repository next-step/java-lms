package nextstep.courses.domain.vo;

public class ImageSize {

    private static final int WIDTH_THRESHOLD = 300;
    private static final int HEIGHT_THRESHOLD = 200;
    private static final int WIDTH_PROPORTION = 3;
    private static final int HEIGHT_PROPORTION = 2;

    private final int width;
    private final int height;


    public ImageSize(int width, int height) {
        if (!satisfy(width, height)) {
            throw new IllegalArgumentException("image size over");
        }

        this.width = width;
        this.height = height;
    }

    private boolean satisfy(int width, int height) {
        return validateSize(width, height) && validateProportion(width, height);
    }


    private boolean validateSize(int width, int height) {
        return width >= WIDTH_THRESHOLD && height >= HEIGHT_THRESHOLD;
    }

    private boolean validateProportion(int width, int height) {
        return (double) width / height == (double) WIDTH_PROPORTION / HEIGHT_PROPORTION;
    }
}
