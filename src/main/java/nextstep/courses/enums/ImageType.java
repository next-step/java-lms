package nextstep.courses.enums;

import java.util.Arrays;

public enum ImageType {
    GIF("GIF"),
    JPG("JPG"),
    JPEG("JPEG"),
    PNG("PNG"),
    SVG("SVG");

    public static final String INVALID_IMAGE_TYPE = "유효하지 않은 이미지 타입 입니다.";
    private String description;

    ImageType(String description) {
        this.description = description;
    }

    public static ImageType from(String imageType) {
        return Arrays.stream(ImageType.values())
                .filter(status -> status.description.equals(imageType))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(INVALID_IMAGE_TYPE));
    }

    public String getImageType() {
        return description;
    }
}
