package nextstep.courses.domain;

public enum ImageType {

    GIF("gif"),
    JPG("jpg", "jpeg"),
    PNG("png"),
    SVG("svg");

    private String[] contentType;

    ImageType(String... contentType) {
        this.contentType = contentType;
    }
}
