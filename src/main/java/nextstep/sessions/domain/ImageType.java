package nextstep.sessions.domain;

import java.util.Arrays;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private String imageType;

    ImageType(String imageType) {
        this.imageType = imageType;
    }

    public static ImageType valueOfType(String imageType) {
        return Arrays.stream(values())
                .filter(value -> value.imageType.equals(imageType))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 이미지 타입입니다."));
    }
}
