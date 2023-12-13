package nextstep.courses.domain.Image;

import nextstep.courses.exception.ImageFormatException;

import java.util.Arrays;

public enum ImageFormat {
    GIF, JPG, JPEG, PNG, SVG;

    public static ImageFormat findBy(String value) {
        return Arrays.stream(ImageFormat.values())
                .filter((i) -> i.toString().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new ImageFormatException("이미지 타입은 gif, jpg(jpeg 포함), png, svg 만 가능합니다."));
    }


}
