package nextstep.courses.domain.enums;

public enum ImageType {
    JPEG("jpeg"),
    PNG("png"),
    GIF("gif");

    private String value;

    ImageType(String value) {
        this.value = value;
    }

    public String getMimeType() {
        return this.value;
    }
}
