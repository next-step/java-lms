package nextstep.utils;

public class TestImage implements Image {
    private final int width;
    private final int height;
    private final int size;
    private final String extension;

    public TestImage(int width, int height, int size, String extension) {
        this.width = width;
        this.height = height;
        this.size = size;
        this.extension = extension;
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String extension() {
        return extension;
    }

    @Override
    public String getPath() {
        return "test/path/to/image." + extension;
    }
}
