package nextstep.images.domain;

import java.util.Arrays;

public enum CoverImageType {

    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String type;

    CoverImageType(final String type) {
        this.type = type;
    }

    public static CoverImageType from(final String type) {
        return Arrays.stream(CoverImageType.values())
            .filter(value -> value.type.equals(type.toLowerCase()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(type + "은 지원하지 않는 이미지 타입입니다."));
    }
}
