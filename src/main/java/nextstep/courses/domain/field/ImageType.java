package nextstep.courses.domain.field;

import java.util.stream.Stream;

public enum ImageType {

    GIF("gif"),
    JPG("jpg"),
    JPGE("jpge"),
    PNG("png"),
    SVG("svg"),
    ;

    private final String extension;

    ImageType(String extension) {
        this.extension = extension;
    }

    public static ImageType getType(String type) {
        return Stream.of(ImageType.values())
                     .filter(extension -> extension.getExtension().equals(type))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 이미지 확장자입니다"));
    }

    public String getExtension() {
        return extension;
    }
}
