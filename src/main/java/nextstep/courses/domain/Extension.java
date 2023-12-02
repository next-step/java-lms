package nextstep.courses.domain;

import nextstep.courses.exception.InvalidImageExtensionException;

import java.util.Arrays;

public enum Extension {
    GIF, JPG, JPEG, PNG, SVG;

    private static final Extension[] VALUES = values();

    public static Extension from(String inputExtension) {
        return Arrays.stream(VALUES)
                .filter(e -> e.name().equals(inputExtension.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new InvalidImageExtensionException());
    }
}
