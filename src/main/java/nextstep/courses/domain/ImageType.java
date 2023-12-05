package nextstep.courses.domain;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String imageType;

    ImageType(String imageType) {
        this.imageType = imageType;
    }
}
