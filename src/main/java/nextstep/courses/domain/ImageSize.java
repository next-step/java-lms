package nextstep.courses.domain;

import java.awt.image.BufferedImage;
import java.io.File;

public class ImageSize {

    public static final int MIN_HEIGHT = 200;
    public static final int MIN_WIDTH = 300;
    private Integer imageWidth;
    private Integer imageHeight;

    public ImageSize(Integer imageWidth, Integer imageHeight) {
        if (imageWidth < MIN_HEIGHT || imageHeight < MIN_WIDTH) {
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
}
