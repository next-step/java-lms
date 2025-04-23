package nextstep.stub.domain;

import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.domain.session.image.SessionImageType;

import java.io.IOException;

public class TestSessionImage extends SessionImage {
    private final int width;
    private final int height;
    private final long byteSize;

    public TestSessionImage(String url, SessionImageType type, int width, int height, long byteSize) throws IOException {
        super(url, type);
        this.width = width;
        this.height = height;
        this.byteSize = byteSize;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public long byteSize() {
        return byteSize;
    }
}
