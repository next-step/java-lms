package nextstep.courses.domain;

import nextstep.courses.exception.InvalidImageExtensionException;

import java.util.Arrays;

public enum Extension {
    GIF, JPG, JPEG, PNG, SVG;

    public static Extension from(String inputExtension) {
        return Arrays.stream(values())
                .filter(e -> e.name().equals(inputExtension))
                .findFirst()
                .orElseThrow(() -> new InvalidImageExtensionException());
    }
}
