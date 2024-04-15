package nextstep.courses.code;

import java.util.Arrays;

public enum ImageType {

    GIF (".gif"),
    JPG (".jpg"),
    JPEG (".jpeg"),
    PNG (".png"),
    SVG (".svg");

    private String extension;
    ImageType(String extension) {
        this.extension = extension;
    }

    public String extension() {
        return extension;
    }

    public static ImageType of(String extension) {
        return Arrays.stream(ImageType.values())
                .filter(imageType -> imageType.extension().equals(extension))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 확장자 입니다."));
    }
}
