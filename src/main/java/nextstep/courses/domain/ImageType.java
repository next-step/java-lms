package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    public static final String NOT_FOUND_TYPE_ERROR_MESSAGE = "은 유효한 타입이 아닙니다.";
    private final String type;

    ImageType(String type) {
        this.type = type;
    }

    public static ImageType from(final String type) {
        return Arrays.stream(ImageType.values())
                .filter(value -> value.isSameType(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(type + NOT_FOUND_TYPE_ERROR_MESSAGE));

    }

    private boolean isSameType(String type) {
        return this.type.equals(type.toLowerCase());
    }
}
