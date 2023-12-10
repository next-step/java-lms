package nextstep.courses.domain.image;

import java.util.Arrays;
import nextstep.courses.exception.InvalidTypeException;

public enum Type {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("sng");

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public static Type value(String name) throws InvalidTypeException {
        return Arrays.stream(values())
            .filter(typeEnum -> typeEnum.type.equals(name))
            .findFirst()
            .orElseThrow(() -> new InvalidTypeException("이미지 타입은 gif, jpg, jpeg, png, svg만 허용합니다"));
    }
}
