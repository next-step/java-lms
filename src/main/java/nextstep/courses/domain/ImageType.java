package nextstep.courses.domain;

public enum ImageType {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG,
    OTHER;

    public static boolean isAllowed(ImageType type) {
        return type == GIF || type == JPG || type == JPEG || type == PNG || type == SVG;
    }
}
