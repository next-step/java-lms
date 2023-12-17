package nextstep.courses.domain.session.constant;

import java.util.Arrays;

public enum ImageTypeEnum {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private String type;

    ImageTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static ImageTypeEnum findByType(String inputType) {
        return Arrays.stream(ImageTypeEnum.values())
                .filter(e -> e.getType().equalsIgnoreCase(inputType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("옳바른 이미지 타입이 아닙니다. 이미지 타입을 확인해주세요."));
    }
}
