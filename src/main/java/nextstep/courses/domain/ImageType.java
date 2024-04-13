package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageType {

    GIF, JPG, JPEG, PNG, SVG;

    public static ImageType of(String type) {
        return Arrays.stream(ImageType.values())
                .filter(imageType -> imageType.name().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("이미지 타입은 gif, jpg, jpeg, png, svg만 허용됩니다."));
    }
}
