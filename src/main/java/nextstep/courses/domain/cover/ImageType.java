package nextstep.courses.domain.cover;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private String value;

    ImageType(String value) {
        this.value = value;
    }
}
