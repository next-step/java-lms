package nextstep.image.domain;

public enum ImageType {

    GIT("git"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    String type;

    ImageType(String type) {
        this.type = type;
    }
}
