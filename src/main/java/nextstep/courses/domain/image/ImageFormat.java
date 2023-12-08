package nextstep.courses.domain.image;

public enum ImageFormat {

    JPEG("jpeg"),
    JPG("jpg"),
    PMG("png"),
    SVG("svg"),
    GIF("gif"),
    ;

    private final String format;

    ImageFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
