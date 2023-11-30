package nextstep.courses.domain.session;

import java.util.Arrays;

public enum ImageType {
    JPG,
    JPEG,
    PNG,
    GIF,
    SVG;

    public static ImageType fromString(String value) {
        return Arrays.stream(values())
                .filter(type -> type.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(value + "is not use. only usable type : jpg, jpeg, png, gif, svg"));
    }
}
