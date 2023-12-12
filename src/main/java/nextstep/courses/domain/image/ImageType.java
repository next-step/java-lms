package nextstep.courses.domain.image;

import java.util.Arrays;
import nextstep.courses.exception.InvalidTypeException;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("sng");

    private final String type;

    ImageType(String type) {
        this.type = type;
    }

    public static ImageType value(String name) throws InvalidTypeException {
        return Arrays.stream(values())
            .filter(typeEnum -> typeEnum.sameType(name))
            .findFirst()
            .orElseThrow(() -> new InvalidTypeException("이미지 타입은 gif, jpg, jpeg, png, svg만 허용합니다"));
    }

    private boolean sameType(String name) {
        return this.type.equals(name);
    }
}
