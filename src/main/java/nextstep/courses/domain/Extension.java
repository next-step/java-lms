package nextstep.courses.domain;

import java.util.Arrays;

public enum Extension {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG;

    public static boolean contains(String inputExtension) {
        return Arrays.stream(values())
                .anyMatch(extension -> extension.name().equals(inputExtension.toUpperCase()));
    }
}
