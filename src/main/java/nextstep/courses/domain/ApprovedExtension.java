package nextstep.courses.domain;

import java.util.Arrays;

public enum ApprovedExtension {

    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String extension;

    ApprovedExtension(String extension) {
        this.extension = extension;
    }

    public static boolean contains(String extension) {
        return Arrays.asList(ApprovedExtension.values()).contains(extension);
    }
}
