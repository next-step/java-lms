package nextstep.courses.domain.session.image;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ImageTypeEnum {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    public static final String INVALID_IMAGE_TYPE = "유효하지 않은 이미지 타입 입니다.";
    private String imageType;

    private static final Map<String, ImageTypeEnum> IMAGE_TYPES = Collections.unmodifiableMap(
            Stream.of(values())
                    .collect(Collectors.toMap(ImageTypeEnum::getImageType, Function.identity()))
    );

    ImageTypeEnum(String imageType) {
        this.imageType = imageType;
    }

    public String getImageType() {
        return imageType;
    }

    public static ImageTypeEnum of(String imageType) {
        imageType = imageType.toLowerCase();

        if (IMAGE_TYPES.containsKey(imageType)) {
            return IMAGE_TYPES.get(imageType);
        }

        throw new IllegalArgumentException(INVALID_IMAGE_TYPE);
    }

}
