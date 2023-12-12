package nextstep.imgae.domain;

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

    public String getType() {
        return type;
    }

    public static ImageType matchType(String type) {
        return Arrays.stream(values())
                .filter(imageType -> imageType.getType().equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("이미지 타입이 올바르지 않습니다."));
    }
}
