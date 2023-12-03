package nextstep.lms.enums;

import java.util.Arrays;

public enum ExtensionEnum {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG;

    public static boolean contains(String value) {
        return Arrays.stream(ExtensionEnum.values())
                .anyMatch(extension -> extension.name().equalsIgnoreCase(value));
    }
}
