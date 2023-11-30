package nextstep.sessions.domain;

import java.util.Arrays;

import org.springframework.util.StringUtils;
import nextstep.sessions.domain.exception.SessionsException;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    ImageType(String name) {
        this.name = name;
    }

    private final String name;

    public static ImageType valueOfName(String name) {
        String ext = StringUtils.getFilenameExtension(name);
        return Arrays.stream(values())
            .filter(v -> v.name.equals(ext))
            .findFirst()
            .orElseThrow(() -> new SessionsException("지원하지 않는 이미지 타입입니다."));
    }

}
