package nextstep.courses.domain;

import java.awt.image.BufferedImage;

public class ImageSize {

    public static final int MIN_HEIGHT = 200;
    public static final int MIN_WIDTH = 300;
    private Integer imageWidth;
    private Integer imageHeight;

    public ImageSize(Integer imageWidth, Integer imageHeight) {
        if (imageWidth < MIN_WIDTH || imageHeight < MIN_HEIGHT) {
            throw new IllegalArgumentException();
        }
        if ((double) imageWidth / imageHeight != 1.5) {
            throw new IllegalArgumentException();
        }
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public ImageSize(BufferedImage image) {
        this(image.getWidth(), image.getHeight());
    }

    public Integer getImageWidth() {
        return imageWidth;
    }

    public Integer getImageHeight() {
        return imageHeight;
    }
}
