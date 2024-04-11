package nextstep.courses.domain;

import java.util.HashMap;
import java.util.Map;

public enum ImageType {

    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private static final Map<String, ImageType> map = new HashMap<>() {
        {
            put("gif", GIF);
            put("jpg", JPG);
            put("jpeg", JPEG);
            put("png", PNG);
            put("svg", SVG);
        }
    };
    private final String type;

    ImageType(String type) {
        this.type = type;
    }

    public static ImageType getImageType(String type) {
        type = type.toLowerCase();
        ImageType imageType = map.get(type);
        if (imageType == null) {
            throw new IllegalArgumentException("지원하지않는 이미지 타입입니다.");
        }
        return imageType;
    }
}
