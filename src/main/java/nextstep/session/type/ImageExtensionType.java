package nextstep.session.type;

import java.util.Arrays;

public enum ImageExtensionType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg")
    ;

    private final String extension;

    ImageExtensionType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return "." + this.extension;
    }

    public static ImageExtensionType of(String extension) {
        return Arrays.stream(ImageExtensionType.values())
                .filter(type -> type.extension.equals(extension))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
