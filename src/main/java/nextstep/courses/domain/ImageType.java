package nextstep.courses.domain;

public enum ImageType {
    GIF("gif"),
    JPG("jpg"),
    PNG("png"),
    SVG("svg");

    private final String type;

    ImageType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }
}
