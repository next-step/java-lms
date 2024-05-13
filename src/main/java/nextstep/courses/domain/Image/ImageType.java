package nextstep.courses.domain.Image;

import java.util.Arrays;
import nextstep.courses.NotAcceptedImageTypeException;

public enum ImageType {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG;

    public static ImageType of(String type) throws NotAcceptedImageTypeException {
        return Arrays.stream(ImageType.values())
            .filter(value -> value.name().equals(type))
            .findFirst()
            .orElseThrow(() -> new NotAcceptedImageTypeException("허용되지 않는 이미지 타입입니다."));
    }
}
