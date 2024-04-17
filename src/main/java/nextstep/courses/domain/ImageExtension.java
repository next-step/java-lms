package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageExtension {
    GIF("gif"), JPG("jpg"), JPEG("jpeg"), PNG("png"), SVG("svg");

    private final String value;

    ImageExtension(String value) {
        this.value = value.toLowerCase();
    }

    public static ImageExtension of(String value){
        return Arrays.stream(values())
                .filter(imageExtension -> imageExtension.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("허용하지 않은 이미지 확장자입니다."));
    }
}
