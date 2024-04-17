package nextstep.courses.domain;

import java.util.Arrays;
import java.util.Optional;

public enum ImageExtension {

    GIF,
    JPG,
    JPEG,
    PNG,
    SVG;

    public static boolean isAllowed(String extension) {
        Optional<ImageExtension> findExtension = Arrays.stream(values())
                .filter(imageExtension -> imageExtension.name().equalsIgnoreCase(extension))
                .findAny();

        return findExtension.isPresent();
    }
}
