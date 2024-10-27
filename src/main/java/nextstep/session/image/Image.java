package nextstep.session.image;

public class Image {

    private static final String NOT_CORRECT_RATE = "이미지의 너비 높이가 3:2 비율이여야 합니다.";

    private final Long id;
    private final String name;
    private final ImageWidth width;
    private final ImageHeight height;
    private final ImageSize size;

    public Image(Long id, String name, int width, int height, int size) {
        ImageExtension.confirmImageExtension(name);
        confirmRate(width, height);
        this.id = id;
        this.name = name;
        this.width = new ImageWidth(width);
        this.height = new ImageHeight(height);
        this.size = new ImageSize(size);
    }

    private void confirmRate(int width, int height) {
        if (width * 2 != height * 3) {
            throw new IllegalArgumentException(NOT_CORRECT_RATE);
        }
    }

}
