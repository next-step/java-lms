package nextstep.courses.domain;

import java.awt.*;
import java.util.Arrays;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");
    private String imageType;

    ImageType(String imageType) {
        this.imageType = imageType;
    }

    public static ImageType of(String matchImageType){
        return Arrays.stream(ImageType.values())
                .filter(imageType -> imageType.isSameMatchImageType(matchImageType.toLowerCase()))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용한다"));
    }

    private boolean isSameMatchImageType(String matchImageType){
        return this.imageType == matchImageType;
    }
}
