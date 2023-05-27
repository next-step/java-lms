package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageExtension {

    JPEG("jpeg"),
    PNG("png");

    private static final String DELIMITER = ".";

    private final String extension;

    ImageExtension(String extension) {
        this.extension = extension;
    }

    public String value() {
        return extension;
    }

    private static String splitFile(String fileName) {
        return fileName.substring(fileName.lastIndexOf(DELIMITER) + 1);
    }

    public static ImageExtension validateExtension(String fileName) {
        String ext = splitFile(fileName);
        return Arrays.stream(values())
                .filter(imageExtension -> imageExtension.value().equals(ext.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("not support image extension"));
    }
}
