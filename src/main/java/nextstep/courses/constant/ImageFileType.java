package nextstep.courses.constant;

public enum ImageFileType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private final String fileType;

    ImageFileType(String fileType) {
        this.fileType = fileType;
    }
}
