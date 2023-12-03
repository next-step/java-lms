package nextstep.courses.domain.field;

import java.util.stream.Stream;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPGE("jpge"),
    PNG("png"),
    SVG("svg");

    private String extension;


    ImageType(String extension) {
        this.extension = extension;
    }

    public static ImageType of (String extension) {
        return Stream.of(valueOf(extension))
                .filter(imagetype ->
                    imagetype.extension.equals(extension))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("옳지 않은 확장자입니다."));
    }
}
