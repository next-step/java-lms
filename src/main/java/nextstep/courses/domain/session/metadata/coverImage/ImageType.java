package nextstep.courses.domain.session.metadata.coverImage;

import java.util.Arrays;
import java.util.List;

public enum ImageType {
    GIF("gif"),
    JPG("jpg,jpeg"),
    PNG("png"),
    SVG("svg");

    private final List<String> extensions;

    ImageType(String extensions) {
        this.extensions = List.of(extensions.split(","));
    }

    public static ImageType fromExtension(String extension) {
        return Arrays.stream(ImageType.values())
            .filter(it -> it.extensions.contains(extension.toLowerCase()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 확장자입니다."));
    }
}
