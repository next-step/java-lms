package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageExtension {
    GIF, JPG, JPEG, PNG, SVG;

    public static ImageExtension from(String input) {
        return Arrays.stream(ImageExtension.values())
                .filter(imageExtension -> imageExtension.name().equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 확장자입니다 : " + input));
    }
}
