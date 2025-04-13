package nextstep.courses.domain;

public enum ImageExtension {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String value;

    ImageExtension(String value) {
        this.value = value;
    }

}
