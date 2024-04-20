package nextstep.courses.domain.image;

public class Image {
    private final Size size;
    private final Type type;
    private final Width width;
    private final Height height;

    public Image(int size, String type, int width, int height) {
        this.size = new Size(size);
        this.type = Type.find(type);
        this.width = new Width(width);
        this.height = new Height(height);

        validateWidthHeightRatio(this.width, this.height);
    }

    private void validateWidthHeightRatio(Width width, Height height) {
        if (width.getValue() / 3 != height.getValue() / 2) {
            throw new RuntimeException();
        }
    }
}
