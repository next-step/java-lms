package nextstep.courses.domain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.springframework.core.io.ClassPathResource;

public class Image {

    private static final String ROOT_PATH = "/src/main/resources/";
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double RATIO = (double) Math.round(3.0 / 2.0 * 100) / 100;
    private static final int MAX_FILE_SIZE = 1;
    private static final List<String> allowedExtensions = List.of("gif", "jpg", "jpeg", "png", "svg");
    private final File file;

    public Image(String fileName) {
        try {
            validate(fileName);
            ClassPathResource resource = new ClassPathResource(fileName);
            this.file = new File(resource.getPath());
        } catch (IOException e) {
            throw new IllegalArgumentException("파일이 존재 하지 않습니다.");
        }
    }

    private void validate(String fileName) throws IOException {
        if (!isValidExtension(fileName)) {
            throw new IllegalArgumentException(String.format("파일 확장자가 올바르지 않습니다. 해당 파일 확장자는 %s 입니다.", extractExtension(fileName)));
        }

        if (!isValidRatio(fileName)) {
            throw new IllegalArgumentException(String.format("강의 커버 이미지의 비율은 %f 올바르지 않습니다.", RATIO));
        }

        if (!isValidSize(fileName)) {
            throw new IllegalArgumentException(
                    String.format("강의 커버 이미지의 최소 가로길이 %d, 세로길이 %d 이상이여야 합니다.", MIN_WIDTH, MIN_HEIGHT));
        }

        if (!isValidVolume(fileName)) {
            throw new IllegalArgumentException(
                    String.format("강의 커버 이미지는 최대 크기는 %d 입니다.", MAX_FILE_SIZE));
        }

    }

    private boolean isValidSize(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        BufferedImage image = ImageIO.read(resource.getFile());
        return image.getWidth() >= MIN_WIDTH && image.getHeight() >= MIN_HEIGHT;
    }

    private boolean isValidRatio(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        BufferedImage image = ImageIO.read(resource.getFile());
        return (double) Math.round((double) image.getWidth() / image.getHeight() * 100) / 100 == RATIO;
    }

    private boolean isValidVolume(String fileName) throws IOException {
        return toMbByte(fileName) < MAX_FILE_SIZE;
    }

    private long toMbByte(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        return resource.getFile().length() / (1_000 * 1_000);
    }

    private boolean isValidExtension(String fileName) {
        String extension = extractExtension(fileName);
        return allowedExtensions.contains(extension);
    }

    private String extractExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
