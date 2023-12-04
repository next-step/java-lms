package nextstep.courses.domain.image;

import java.util.Arrays;
import java.util.List;

public enum ImageType {
    GIF("gif"), JPG("jpg"), JPEG("jpeg"), PNG("png"), SVG("svg");

    private static final ImageType[] imageTypes = values();
    public static final String INVALID_FILE_TYPE_MSG = "올바른 파일 확장자가 아닙니다.";
    private final String name;
    ImageType(final String name) {
        this.name = name;
    }

    public static ImageType of(final String name) {
        return Arrays.stream(imageTypes)
                .filter(n -> n.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_FILE_TYPE_MSG));
    }
}
