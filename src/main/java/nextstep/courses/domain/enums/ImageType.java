package nextstep.courses.domain.enums;

import java.util.Arrays;

public enum ImageType {
    JPEG("jpeg"),
    PNG("png"),
    GIF("gif");

    private String value;

    ImageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static boolean isValidType(String type) {
        return Arrays.stream(values())
                .anyMatch(validType -> validType.getValue().equals(type.toLowerCase()));
    }

}
