package nextstep.courses.domain.image;

import java.util.Arrays;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private static final String INVALID_TYPE_MESSAGE = "이미지 타입을 확인해주세요.";
    private static final ImageType[] imageTypes = values();

    private final String typeName;

    ImageType(final String typeName) {
        this.typeName = typeName;
    }

    public static ImageType of(final String name) {
        return Arrays.stream(imageTypes)
                     .filter(imageType -> imageType.typeName.equals(name))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException(INVALID_TYPE_MESSAGE));
    }
}
