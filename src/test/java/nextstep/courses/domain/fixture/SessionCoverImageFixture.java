package nextstep.courses.domain.fixture;

import nextstep.courses.domain.session.image.SessionCoverImage;

import static nextstep.courses.domain.session.image.ImageFileSize.MAX_FILE_SIZE;
import static nextstep.courses.domain.session.image.ImageSize.MIN_HEIGHT;
import static nextstep.courses.domain.session.image.ImageSize.MIN_WIDTH;

public class SessionCoverImageFixture {

    private static final String EXTENSION = "gif";

    public static SessionCoverImage coverImage(long size) {
        return new SessionCoverImage(size, MIN_WIDTH, MIN_HEIGHT, EXTENSION);
    }

    public static SessionCoverImage coverImage(int width, int height) {
        return new SessionCoverImage(MAX_FILE_SIZE, width, height, EXTENSION);
    }

    public static SessionCoverImage coverImage(String extension) {
        return new SessionCoverImage(MAX_FILE_SIZE, MIN_WIDTH, MIN_HEIGHT, extension);
    }

    public static SessionCoverImage coverImage() {
        return new SessionCoverImage(MAX_FILE_SIZE, MIN_WIDTH, MIN_HEIGHT, EXTENSION);
    }

}
