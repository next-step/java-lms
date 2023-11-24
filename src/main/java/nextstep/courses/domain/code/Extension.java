package nextstep.courses.domain.code;

import nextstep.courses.exception.NotExtensionTypeException;

import java.util.Arrays;

public enum Extension {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String stringExtension;

    Extension(String stringExtension) {
        this.stringExtension = stringExtension;
    }

    public static Extension of(String extension) {
        return Arrays.stream(Extension.values())
                .filter(e -> e.stringExtension.equals(extension))
                .findFirst()
                .orElseThrow(() -> new NotExtensionTypeException("올바르지 않은 파일 타입입니다."));
    }
}
