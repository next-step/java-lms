package nextstep.sessions.domain;

import java.util.Arrays;

public enum ImageType {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG;

    public static ImageType from(String type) {
        return Arrays.stream(values())
                .filter(imageType -> imageType.toString().toLowerCase().equals(type.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s는 허용하지 않는 이미지 타입입니다.", type)));
    }
}
