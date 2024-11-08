package nextstep.courses.domain;

import nextstep.courses.InvalidCoverImageException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CoverImage {
    private static final int LIMIT_OF_BYTES = 1048576;
    private static final int MINIMUM_OF_WIDTH_PIXEL = 300;
    private static final int MINIMUM_OF_HEIGHT_PIXEL = 200;
    private static final int RATIO_OF_WIDTH = 3;
    private static final int RATIO_OF_HEIGHT = 2;
    private static final List<String> VALID_EXTENSIONS = List.of("gif", "jpg", "jpeg", "png", "svg");
    private String coverImage;

    public CoverImage() {}

    public CoverImage(String coverImage) throws IOException {
        this.validate(coverImage);

        this.coverImage = coverImage;
    }

    private void validate(String coverImage) throws IOException {
        if (this.isInvalidExtension(coverImage)) {
            throw new InvalidCoverImageException("유효하지 않은 확장자의 커버 이미지입니다.");
        }

        if (this.isInvalidVolume(coverImage)) {
            throw new InvalidCoverImageException("유효하지 않은 용량의 커버 이미지입니다.");
        }

        if (this.isInvalidSize(coverImage)) {
            throw new InvalidCoverImageException("유효하지 않은 크기의 커버 이미지입니다.");
        }

        if (this.isInvalidRatio(coverImage)) {
            throw new InvalidCoverImageException("유효하지 않은 비율의 커버 이미지입니다.");
        }
    }

    private boolean isInvalidExtension(String coverImage) {
        return ! VALID_EXTENSIONS.contains(this.getExtension(coverImage));
    }

    private boolean isInvalidVolume(String coverImage) {
        return new File(coverImage).length() > LIMIT_OF_BYTES;
    }

    private boolean isInvalidSize(String coverImage) throws IOException {
        BufferedImage image = ImageIO.read(new File(coverImage));

        if (image.getWidth() < MINIMUM_OF_WIDTH_PIXEL) {
            return true;
        }

        return image.getHeight() < MINIMUM_OF_HEIGHT_PIXEL;
    }

    private boolean isInvalidRatio(String coverImage) throws IOException {
        BufferedImage image = ImageIO.read(new File(coverImage));

        return image.getHeight() / image.getWidth() != RATIO_OF_HEIGHT / RATIO_OF_WIDTH;
    }

    private String getExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf(".");
        if (lastIndexOfDot == -1) {
            return null;
        }
        return fileName.substring(lastIndexOfDot + 1);
    }
}
