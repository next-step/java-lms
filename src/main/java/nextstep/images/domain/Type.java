package nextstep.images.domain;

import java.util.Arrays;

public enum Type {

    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String type;

    Type(final String type) {
        this.type = type;
    }

    public static Type from(final String type) {
        return Arrays.stream(Type.values())
            .filter(value -> value.type.equals(type))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 이미지 타입입니다."));
    }
}
