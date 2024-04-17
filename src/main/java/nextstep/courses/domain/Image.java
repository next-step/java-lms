package nextstep.courses.domain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.springframework.core.io.ClassPathResource;

public class Image {

    private final File file;

    public Image(String fileName) {
        try {
            ImageInfo.validate(fileName);
            ClassPathResource resource = new ClassPathResource(fileName);
            this.file = new File(resource.getPath());
        } catch (IOException e) {
            throw new IllegalArgumentException("파일이 존재 하지 않습니다.");
        }
    }

    private static class ImageInfo {

        private static final int MIN_WIDTH = 300;
        private static final int MIN_HEIGHT = 200;
        private static final double RATIO = (double) Math.round(3.0 / 2.0 * 100) / 100;
        private static final int MAX_FILE_SIZE = 1;

        public static boolean isValidSize(String fileName) throws IOException {
            ClassPathResource resource = new ClassPathResource(fileName);
            BufferedImage image = ImageIO.read(resource.getFile());
            return image.getWidth() >= MIN_WIDTH && image.getHeight() >= MIN_HEIGHT;
        }


        public static boolean isValidRatio(String fileName) throws IOException {
            ClassPathResource resource = new ClassPathResource(fileName);
            BufferedImage image = ImageIO.read(resource.getFile());
            return (double) Math.round((double) image.getWidth() / image.getHeight() * 100) / 100 == RATIO;
        }

        public static boolean isValidVolume(String fileName) throws IOException {
            return toMbByte(fileName) < MAX_FILE_SIZE;
        }

        private static void validate(String fileName) throws IOException {
            if (!isValidExtension(fileName)) {
                throw new IllegalArgumentException(
                        String.format("파일 확장자가 올바르지 않습니다. 해당 파일 확장자는 %s 입니다.", extractExtension(fileName)));
            }

            if (!ImageInfo.isValidRatio(fileName)) {
                throw new IllegalArgumentException(String.format("강의 커버 이미지의 비율은 %f 올바르지 않습니다.", RATIO));
            }

            if (!ImageInfo.isValidSize(fileName)) {
                throw new IllegalArgumentException(
                        String.format("강의 커버 이미지의 최소 가로길이 %d, 세로길이 %d 이상이여야 합니다.", MIN_WIDTH, MIN_HEIGHT));
            }

            if (!ImageInfo.isValidVolume(fileName)) {
                throw new IllegalArgumentException(
                        String.format("강의 커버 이미지는 최대 크기는 %d 입니다.", MAX_FILE_SIZE));
            }
        }

        private static boolean isValidExtension(String fileName) {
            String extension = extractExtension(fileName);
            return ImageExtension.isAllowed(extension);
        }

        private static String extractExtension(String fileName) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }

        private static long toMbByte(String fileName) throws IOException {
            ClassPathResource resource = new ClassPathResource(fileName);
            return resource.getFile().length() / (1_000 * 1_000);
        }
    }
}

