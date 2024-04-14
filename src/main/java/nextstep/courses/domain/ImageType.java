package nextstep.courses.domain;

import java.util.Arrays;
import java.util.List;

public enum ImageType {

    GIF(List.of("gif")),
    JPG(List.of("jpg", "jpeg")),
    PNG(List.of("png")),
    SVG(List.of("svg"));

    private List<String> contentType;

    ImageType(List<String> contentType) {
        this.contentType = contentType;
    }

    public static boolean isValidType(String type) {
        return Arrays.stream(ImageType.values())
            .filter(imageType -> imageType.isContain(type))
            .findAny()
            .isPresent();
    }

    private boolean isContain(String type) {
        return this.contentType.contains(type);
    }
}
