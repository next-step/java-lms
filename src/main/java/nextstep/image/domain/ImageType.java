package nextstep.image.domain;

import java.util.Arrays;
import nextstep.image.exception.CannotFindImageTypeException;

public enum ImageType {

    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    public static final String CANNOT_FIND_IMAGE_TYPE_EXCEPTION = "해당 이미지 타입을 찾을 수 없습니다.";

    private final String name;

    ImageType(String name) {
        this.name = name;
    }

    public static ImageType findByName(String name) {
        return Arrays.stream(values())
                .filter(imageType -> imageType.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new CannotFindImageTypeException(CANNOT_FIND_IMAGE_TYPE_EXCEPTION));
    }
}
