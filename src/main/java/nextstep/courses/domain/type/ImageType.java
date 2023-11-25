package nextstep.courses.domain.type;

import nextstep.courses.InvalidImageTypeException;

import java.util.Arrays;

public enum ImageType {

    GIF, JPG, JPEG, PNG, SVG;

    public static ImageType of(String typeText) {
        return Arrays.stream(ImageType.values())
            .filter(type -> type.name().equals(typeText))
            .findFirst()
            .orElseThrow(() -> new InvalidImageTypeException("지원하지 않는 이미지 타입입니다."));
    }

}
