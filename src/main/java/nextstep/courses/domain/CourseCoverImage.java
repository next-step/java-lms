package nextstep.courses.domain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class CourseCoverImage {

    private static final String[] SUPPORTED_IMAGE_EXTENSIONS = {".gif", ".jpg", ".jpeg", ".png", ".svg"};
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;

    private String imageFilePath;
    private File imageFile;

    private BufferedImage courseCoverImageData;

    public CourseCoverImage(String imageFilePath) {
        validateImage(imageFilePath);
        this.imageFilePath = imageFilePath;
    }

    private void validateImage(String imageFilePath) {
        if (imageFilePath == null) {
            throw new IllegalArgumentException("강의 커버 이미지는 비어있을 수 없습니다.");
        }

        if (Arrays.stream(SUPPORTED_IMAGE_EXTENSIONS).noneMatch(imageFilePath::endsWith)) {
            throw new IllegalArgumentException("지원하지 않는 이미지 형식입니다. (지원 형식: gif, jpg, jpeg, png, svg)");
        }

        try {
            imageFile = new File(imageFilePath);
            if (!imageFile.exists()) {
                throw new IllegalArgumentException("강의 커버 이미지를 읽을 수 없습니다.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("강의 커버 이미지를 읽을 수 없습니다.");
        }

        try {
            courseCoverImageData = ImageIO.read(imageFile);
            if (courseCoverImageData == null) {
                throw new IllegalArgumentException("강의 커버 이미지를 읽을 수 없습니다.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("강의 커버 이미지를 읽을 수 없습니다.");
        }

        if (courseCoverImageData.getWidth() < MIN_WIDTH || courseCoverImageData.getHeight() < MIN_HEIGHT) {
            throw new IllegalArgumentException("강의 커버 이미지는 최소 300x200 픽셀 이상이어야 합니다.");
        }
    }
}
