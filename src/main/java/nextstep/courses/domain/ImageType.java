package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private String type;

    ImageType(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.type;
    }

    public static ImageType of(String type) {
        return Arrays.stream(values())
                .filter(imageType -> imageType.getValue().equals(type.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 이미지 형식입니다."));
    }
}
