package nextstep.courses.domain.session.cover;

import java.util.Arrays;

public enum ImageType {

    GIF("gif"),
    JPG("jpg", "jpeg"),
    PNG("png"),
    SVG("svg");

    private final String[] extensions;

    ImageType(String... extensions) {
        this.extensions = extensions;
    }

    private boolean matches(String extension) {
        return Arrays.stream(extensions)
                .anyMatch(ext -> ext.equalsIgnoreCase(extension));
    }

    private static ImageType from(String extension) {
        return Arrays.stream(values())
                .filter(type -> type.matches(extension))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public static ImageType fromFileName(String fileName) {
        String extension = extractExtension(fileName);
        return from(extension);
    }

    private static String extractExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index == -1 || index == fileName.length() - 1) {
            throw new IllegalArgumentException();
        }
        return fileName.substring(index + 1);
    }

}
