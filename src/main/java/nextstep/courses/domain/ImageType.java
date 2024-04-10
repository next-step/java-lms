package nextstep.courses.domain;

import java.util.Arrays;
import java.util.Optional;

public enum ImageType {

    GIF, JPG, JPEG, PNG, SVG;

    public static ImageType of(String type) {
        if (Arrays.stream(ImageType.values())
                .noneMatch(imageType -> imageType.name().equals(type.toUpperCase()))) {
            throw new IllegalArgumentException("이미지 타입은 gif, jpg, jpeg, png, svg만 허용됩니다.");
        }
        return ImageType.valueOf(type.toUpperCase());
    }
}
