package nextstep.courses.domain.cover;

import java.util.EnumMap;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");
    private String value;

    private static final EnumMap<ImageType, Boolean> imageTypeCache = new EnumMap<>(
        ImageType.class);

    static {
        for (ImageType imageType : values()) {
            imageTypeCache.put(imageType, true);
        }
    }

    ImageType(String value) {
        this.value = value;
    }

    public static boolean findAvailableImagesType(ImageType imageType) {
        return imageTypeCache.getOrDefault(imageType, false);
    }

    public String getValue() {
        return value;
    }
}
