package nextstep.courses.domain;

import java.util.List;

public enum ImageType {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG,
    ETC;

    public static boolean isValid(ImageType type) {
        return List.of(GIF, JPG, JPEG, PNG, SVG).contains(type);
    }
}
