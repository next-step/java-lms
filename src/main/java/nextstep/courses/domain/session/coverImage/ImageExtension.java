package nextstep.courses.domain.session.coverImage;

import java.util.Arrays;
import java.util.List;

public enum ImageExtension {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG,
    BMP;

    private static final List<ImageExtension> VALID_EXTENSION = Arrays.asList(GIF, JPG, JPEG, PNG, SVG);

    public static boolean validExtension(ImageExtension extension) {
        if (!VALID_EXTENSION.contains(extension)) {
            throw new IllegalArgumentException("허용되지 않은 확장자 입니다.");
        }
        return true;
    }

}
