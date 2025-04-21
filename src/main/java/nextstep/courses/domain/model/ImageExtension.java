package nextstep.courses.domain.model;

import java.util.Arrays;

public enum ImageExtension {
    GIF, JPG, JPEG, PNG, SVG;

    public static boolean notExist(String s) {
        return !exist(s);
    }

    public static boolean exist(String s) {
        return Arrays.stream(values())
                .anyMatch(imageExtension -> imageExtension.name().equalsIgnoreCase(s));
    }
}
