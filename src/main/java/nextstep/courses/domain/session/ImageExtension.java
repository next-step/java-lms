package nextstep.courses.domain.session;

import java.util.Arrays;

public enum ImageExtension {

    // "gif", "jpg", "jpeg", "png", "svg"
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg")
    ;

    private final String extension;

    ImageExtension(String extension) {
        this.extension = extension;
    }

    public static boolean isPossibleExtension(String compareExtension) {
        return Arrays.stream(values())
                .anyMatch(extension -> extension.extension.equals(compareExtension.toLowerCase()));
    }
}
