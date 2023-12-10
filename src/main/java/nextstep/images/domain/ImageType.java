package nextstep.images.domain;

import java.util.Arrays;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg"),
    ;
    private final String type;

    ImageType(String type) {
        this.type = type;
    }

    public static ImageType of(String type) {
        return Arrays.stream(values())
                .filter(imageType -> imageType.type.equals(type))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException("허용되지 않은 이미지 타입입니다."));
    }
}
