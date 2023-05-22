package nextstep.courses.domain.enums;

import java.util.Arrays;

public enum ImageType {
    JPEG("jpeg"),
    PNG("png"),
    GIF("gif");

    private String value;

    ImageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static ImageType of(String value) {
        return Arrays.stream(values())
                .filter(imageType -> imageType.getValue().equals(value.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 이미지 형식입니다."));
    }

    public static boolean isValidType(String type) {
        return Arrays.stream(values())
                .anyMatch(validType -> validType.getValue().equals(type.toLowerCase()));
    }

}
