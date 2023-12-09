package nextstep.session.domain.fixture;

import nextstep.session.domain.SessionImage;

public class SessionImageFixture {
    public static final String DEFAULT_IMAGE_URL = "S3://test.gif";
    public static final int DEFAULT_IMAGE_SIZE = 1024 * 1024;
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    public static final SessionImage sessionImageFixture = SessionImage.of(DEFAULT_IMAGE_URL, DEFAULT_IMAGE_SIZE, DEFAULT_WIDTH, DEFAULT_HEIGHT);

    public static SessionImage createSessionImage() {
        return SessionImage.of(DEFAULT_IMAGE_URL, DEFAULT_IMAGE_SIZE, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public static SessionImage createSessionImage(String fileURL) {
        return SessionImage.of(fileURL, DEFAULT_IMAGE_SIZE, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public static SessionImage createSessionImage(int imageSize, int width, int height) {
        return SessionImage.of(DEFAULT_IMAGE_URL, imageSize, width, height);
    }
}
