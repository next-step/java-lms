package nextstep.courses.domain.coverimage;

import nextstep.courses.UnsupportedImageTypeException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private static final String ERR_UNSUPPORTED_TYPE = "%s 타입의 이미지는 허용되지 않습니다. 커버 이미지는 아래의 타입들만 허용됩니다: %s";
    private final String name;

    ImageType(String name) {
        this.name = name;
    }

    public static final ImageType of(String name) throws UnsupportedImageTypeException {
        return Arrays.stream(values())
                .filter(element -> element.name == name)
                .findFirst()
                .orElseThrow(() -> new UnsupportedImageTypeException(String.format(ERR_UNSUPPORTED_TYPE, name, allowedTypeName().toString())));
    }

    private static final List<String> allowedTypeName() {
        return Arrays.stream(values())
                .map(type -> type.name())
                .collect(Collectors.toList());
    }
}
