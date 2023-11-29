package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageType {
    GIF("gif"),
    PNG("png"),
    JPEG("jpeg"),
    JPG("jpg"),
    SVG("svg");

    private String type;

    ImageType(String type) {
        this.type = type;
    }

    public boolean sameType(String type){
        return this.type.equals(type);
    }

    public ImageType getType(String type) {
        return Arrays.stream(values())
                .filter(imageType -> imageType.sameType(type))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("지원하지 않는 이미지 타입입니다."));
    }
}
