package nextstep.courses.domain.image;

import nextstep.courses.InvalidImageFormatException;

import java.util.Arrays;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private String typeName;

    ImageType(String imageType) {
        this.typeName = imageType;
    }

    public static ImageType validateImageType(String inputType) throws InvalidImageFormatException {
        return Arrays.stream(ImageType.values())
                .filter(imageType -> imageType.typeName.equals(inputType.toLowerCase()))
                .findAny()
                .orElseThrow(() -> new InvalidImageFormatException(String.format("%s는 지원하지 않는 이미지 타입입니다.", inputType)));
    }
}
