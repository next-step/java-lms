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

    private final String text;
    ImageExtension(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    private static final Map<String, ImageExtension> IMAGE_EXTENSION_MAP =
            Collections.unmodifiableMap(
                    Stream.of(values())
                            .collect(Collectors.toMap(ImageExtension::getText, Function.identity()))
            );

    public static Boolean isInvalidImageExtension(String text) {
        return !IMAGE_EXTENSION_MAP.containsKey(text.toLowerCase());
    }

    public static ImageExtension getExtension(String text) {
        return IMAGE_EXTENSION_MAP.get(text.toLowerCase());
    }


}
