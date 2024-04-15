package nextstep.courses.domain.image;

import nextstep.courses.exception.SessionCoverImageException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ImageExtension {

    // "gif", "jpg", "jpeg", "png", "svg"
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg")
    ;

    private final String value;

    ImageExtension(String extension) {
        this.value = extension;
    }

    public static ImageExtension get(String extension) {
        return Arrays.stream(values())
                .filter(imageExtension -> imageExtension.value.equalsIgnoreCase(extension))
                .findAny()
                .orElseThrow(() -> new SessionCoverImageException(extensionValues(), extension));
    }

    private static List<String> extensionValues() {
        return Arrays.stream(values())
                .map(extension -> extension.value)
                .collect(Collectors.toUnmodifiableList());

    }

    public String get() {
        return value;
    }

}
