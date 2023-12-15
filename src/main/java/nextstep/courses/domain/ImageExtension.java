package nextstep.courses.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ImageExtension {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private static final Map<String, ImageExtension> extensionCacheMap = Arrays.stream(ImageExtension.values()).collect(
            Collectors.toMap(extension -> extension.name, Function.identity()));


    private final String name;

    ImageExtension(String name) {
        this.name = name;
    }

    public static ImageExtension from(String extension) {
        if(extensionCacheMap.containsKey(extension)){
            return extensionCacheMap.get(extension);
        }
        throw new IllegalArgumentException(ExceptionMessage.IMAGE_EXTENSION_NOT_FOUND_TYPE.getMessage());
    }
}
