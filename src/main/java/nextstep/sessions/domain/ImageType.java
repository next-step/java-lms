package nextstep.sessions.domain;

import java.util.Arrays;

public enum ImageType {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG;

    public static ImageType from(String type) {
        return Arrays.stream(values())
                .filter(imageType -> imageType.toString().toLowerCase().equals(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
