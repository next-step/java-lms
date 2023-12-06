package nextstep.courses.domain.session;

import java.util.Arrays;

public enum ImgType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String type;

    ImgType(String type) {
        this.type = type;
    }

    public static ImgType findType(String type) {
        return Arrays.stream(ImgType.values())
                .filter(imgType -> imgType.type.equals(type.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 데이터 타입입니다."));
    }
}
