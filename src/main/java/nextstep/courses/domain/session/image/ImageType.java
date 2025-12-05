package nextstep.courses.domain.session.image;

import java.util.Arrays;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String value;

    ImageType(String value) {
        this.value = value;
    }

    public static ImageType from(String extension) {
        return Arrays.stream(values())
                .filter(type -> type.value.equalsIgnoreCase(extension))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 이미지 타입: " + extension));
    }

    public String getValue() {
        return value;
    }
}
