package nextstep.courses.domain;

import nextstep.courses.ImageException;

import java.util.Arrays;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String type;

    ImageType(String type) {
        this.type = type;
    }

    public static ImageType of(String type) throws ImageException {
        return Arrays.stream(values())
                .filter(imageType -> imageType.type.equals(type.toLowerCase()))
                .findAny()
                .orElseThrow(() -> new ImageException("허용되지 않은 이미지타입입니다."));
    }
}
