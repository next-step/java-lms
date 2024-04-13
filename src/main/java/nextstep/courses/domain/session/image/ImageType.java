package nextstep.courses.domain.session.image;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ImageType {

    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private static final Map<String, ImageType> imageTypes = Stream.of(values())
            .collect(Collectors.toUnmodifiableMap(imageType -> imageType.typeName, Function.identity()));

    private final String typeName;

    ImageType(final String typeName) {
        this.typeName = typeName;
    }

    public static ImageType from(final String typeName) {
        return Optional.ofNullable(imageTypes.get(typeName))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 커버 이미지 종류입니다. 종류: " + typeName));
    }
}
