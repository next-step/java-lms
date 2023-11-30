package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageExtension {

    JPEG("jpeg"),
    JPG("jpg"),
    PNG("png"),
    GIF("gif");

    private final String description;

    ImageExtension(String description) {
        this.description = description;
    }

    public static boolean isPossible(String input) {
        return Arrays.stream(values())
                .anyMatch(extension -> extension.isMatch(input));
    }

    private boolean isMatch(String input) {
        return this.description.equals(input);
    }
}
