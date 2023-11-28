package nextstep.courses.domain.type;

import nextstep.courses.exception.InvalidImageTypeException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum ImageType {

    GIF, JPG, JPEG, PNG, SVG;

    private static final Map<String, ImageType> imageTypeWithString = new HashMap<>();

    static {
        Arrays.stream(ImageType.values())
            .forEach(imageType -> imageTypeWithString.put(imageType.name(), imageType));
    }

    public static ImageType of(String typeText) {
        if (!imageTypeWithString.containsKey(typeText)) {
            throw new InvalidImageTypeException("지원하지 않는 이미지 타입입니다.");
        }

        return imageTypeWithString.get(typeText);
    }

}
