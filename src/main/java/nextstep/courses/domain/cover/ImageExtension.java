package nextstep.courses.domain.cover;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ImageExtension {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg"),
    ;

    private static final Map<String, ImageExtension> IMAGE_EXTENSION_MAP =
            Collections.unmodifiableMap(
                    Stream.of(values())
                            .collect(Collectors.toMap(ImageExtension::getText, Function.identity()))
            );

    private final String text;

    ImageExtension(String text) {
        this.text = text;
    }

    public static boolean isInvalidImageExtension(String text) {
        return !IMAGE_EXTENSION_MAP.containsKey(text.toLowerCase());
    }

    public static ImageExtension getExtension(String text) {
        if (isInvalidImageExtension(text)) {
            throw new IllegalArgumentException("허용되지 않는 이미지 형식입니다.");
        }
        return IMAGE_EXTENSION_MAP.get(text.toLowerCase());
    }

    public String getText() {
        return text;
    }


}
