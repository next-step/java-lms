package nextstep.courses.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ImageType {

    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private static final Map<String, ImageType> map = Arrays.stream(values())
            .collect(Collectors.toMap(imageType -> imageType.type, Function.identity()));
    private final String type;

    ImageType(String type) {
        this.type = type;
    }

    public static ImageType getImageType(String type) {
        type = type.toLowerCase();
        return Optional.ofNullable(map.get(type))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("지원하지않는 이미지 타입입니다.");
                });
    }

    public String getType() {
        return type;
    }
}
