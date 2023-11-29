package nextstep.courses.domain;

public class Image {
    private int size;
    private String type;
    private String width;
    private String height;

    public Image() {
    }

    public Image(int size, String type, String width, String height) {
        this.size = size;
        this.type = type;
        this.width = width;
        this.height = height;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }
}
