package nextstep.courses.domain.session.coverImage;

import java.util.Arrays;

public enum ImageExtension {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG,
    BMP;

    public static boolean validExtension(ImageExtension extension) {
        if (!Arrays.asList(GIF, JPG, JPEG, PNG, SVG).contains(extension)) {
            throw new IllegalArgumentException("허용되지 않은 확장자 입니다.");
        }
        return true;
    }

}
