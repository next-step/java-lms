package nextstep.courses.domain.vo;

import java.util.Arrays;

public enum Extension {

    GIF,
    JPG,
    JPEG,
    PNG,
    SVG,
    UNDEFINED
    ;

    public static Extension of(String extension) {
        return Arrays.stream(Extension.values())
                .filter(it -> extension.toUpperCase().equals(it.name()))
                .findFirst()
                .orElse(UNDEFINED);
    }
}
