package nextstep.courses.domain.images;

import java.util.List;

public enum ImageType {
    JPEG("image/jpeg", List.of("jpg", "jpeg")),
    PNG("image/png", List.of("png")),
    GIF("image/gif", List.of("gif")),
    SVG("image/svg+xml", List.of("svg"));

    private final String mimeType;
    private final List<String> extensions;

    ImageType(String mimeType, List<String> extensions) {
        this.mimeType = mimeType;
        this.extensions = extensions;
    }

    public static ImageType fromMimeType(String mimeType) {
        for (ImageType type : values()) {
            if (type.mimeType.equalsIgnoreCase(mimeType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unsupported image type: " + mimeType);
    }
}
