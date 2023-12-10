package nextstep.courses.domain.coverImage;

import java.util.Arrays;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String type;

    ImageType(String type) {
        this.type = type;
    }

    public static ImageType findType(String type) {
        return Arrays.stream(ImageType.values())
                .filter(imageType -> imageType.type.equals(type.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 데이터 타입입니다."));
    }
}
