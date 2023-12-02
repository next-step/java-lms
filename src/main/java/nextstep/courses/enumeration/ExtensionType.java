package nextstep.courses.enumeration;

import nextstep.courses.exception.InvalidImageExtensionTypeException;

import java.util.Arrays;
import java.util.List;

public enum ExtensionType {

    GIF,
    JPG,
    JPEG,
    PNG,
    SVG
    ;

    public static final List<ExtensionType> ALL_TYPES = Arrays.asList(ExtensionType.values());

    public static ExtensionType findType(String value) {
        return ALL_TYPES.stream()
                .filter((i) -> i.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new InvalidImageExtensionTypeException("이미지 타입은 gif, jpg(jpeg 포함), png, svg 만 가능합니다."));


    }

}
