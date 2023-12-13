package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageExtension {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    final String name;

    ImageExtension(String name) {
        this.name = name;
    }

    public static ImageExtension from(String extension){
        return Arrays.stream(ImageExtension.values()).filter(e -> e.name.equals(extension)).findFirst().orElseThrow(
                ()->new IllegalArgumentException(ExceptionMessage.IMAGE_EXTENSION_NOT_FOUND_TYPE.getMessage()));
    }
}
