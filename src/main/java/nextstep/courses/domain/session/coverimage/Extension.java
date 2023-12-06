package nextstep.courses.domain.session.coverimage;

import nextstep.courses.exception.ImageFileInfoException;

import java.util.Arrays;

public enum Extension {

    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String val;

    Extension(String val) {
        this.val = val;
    }

    public static Extension extension(String extension) throws ImageFileInfoException {
        return Arrays.stream(values())
            .filter(ext -> ext.val.equals(extension))
            .findFirst()
            .orElseThrow(() -> new ImageFileInfoException(String.format("해당 확장자는 사용할 수 없는 이미지 파일입니다. 현재 확장자 :: %s", extension)));
    }
}
