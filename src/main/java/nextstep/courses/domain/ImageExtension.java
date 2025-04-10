package nextstep.courses.domain;

import static java.util.Arrays.*;

public enum ImageExtension {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String extension;

    ImageExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public static boolean isValidExtension(String extension) {
        for (ImageExtension imageExtension : values()) {
            if (imageExtension.extension.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    public static ImageExtension from(String extension) {
        for (ImageExtension imageExtension : values()) {
            if (imageExtension.extension.equalsIgnoreCase(extension)) {
                return imageExtension;
            }
        }
        throw new IllegalArgumentException("허용되지 않는 파일 확장자입니다.");
    }
} 