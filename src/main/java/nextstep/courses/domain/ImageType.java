package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageType {

    GIF("gif"),
    JPG("jpg", "jpeg"),
    PNG("png"),
    SVG("svg");

    private final String[] extensions;

    ImageType(String... extensions) {
        this.extensions = extensions;
    }

    private boolean matches(String extension) {
        return Arrays.stream(extensions)
                .anyMatch(ext -> ext.equalsIgnoreCase(extension));
    }

    public static ImageType from(String extension) {
        return Arrays.stream(values())
                .filter(type -> type.matches(extension))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

}
