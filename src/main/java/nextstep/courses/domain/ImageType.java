package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg"),
    UNKNOWN("unknown"),
    ;

    private final String extension;

    ImageType(String extension) {
        this.extension = extension;
    }

    public static ImageType getType(String imageType) {
        return Arrays.stream(ImageType.values())
                .filter(type -> type.extension.equalsIgnoreCase(imageType))
                .findFirst()
                .orElse(ImageType.UNKNOWN);
    }
}
