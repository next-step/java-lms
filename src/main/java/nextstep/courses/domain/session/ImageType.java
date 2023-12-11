package nextstep.courses.domain.session;

import nextstep.courses.exception.ImageException;

import static java.util.Arrays.*;

public enum ImageType {
    GIF("GIF"),
    JPG("JPG"),
    JPEG("JPEG"),
    PNG("PNG"),
    SVG("SVG");

    private String type;

    ImageType(String type) {
        this.type = type;
    }

    public static void isSupportImageType(String imageType) {
        stream(ImageType.values())
                .filter(i -> i.type.equals(imageType.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new ImageException("지원하는 이미지 형식이 아닙니다."));

    }

}
