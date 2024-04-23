package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageExtension {

    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String extension;

    ImageExtension(String extension) {
        this.extension = extension;
    }

    public static boolean contains(String input) {
        return Arrays.asList(ImageExtension.values()).stream()
                .anyMatch(imgExt -> imgExt.extension.equals(input));
    }
}
