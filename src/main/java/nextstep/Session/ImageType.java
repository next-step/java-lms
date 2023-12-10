package nextstep.Session;

import java.util.Arrays;

public enum ImageType {
    GIF, JPG, JPEG, PNG, SVG;

    public static ImageType from(final String imageType) {
        return Arrays.stream(ImageType.values())
            .filter(value -> value.toString().toLowerCase().equals(imageType))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(imageType + "는 지원하지 않는 확장자 입니다."));
    }
}
