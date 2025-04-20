package nextstep.stub;

import nextstep.courses.domain.session.image.ImageHandler;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class TestImageHandler implements ImageHandler {
    private final int width;
    private final int height;
    private final long byteSize;

    public TestImageHandler() {
        this(300, 200, 1024L * 866L);
    }

    public TestImageHandler(int width, int height, long byteSize) {
        this.width = width;
        this.height = height;
        this.byteSize = byteSize;
    }

    @Override
    public BufferedImage image(String url) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public long byteSize(String url) {
        return byteSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestImageHandler that = (TestImageHandler) o;
        return width == that.width && height == that.height && byteSize == that.byteSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, byteSize);
    }
}
