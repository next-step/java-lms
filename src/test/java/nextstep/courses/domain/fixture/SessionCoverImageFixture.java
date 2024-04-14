package nextstep.courses.domain.fixture;

import nextstep.courses.domain.image.SessionCoverImage;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;
import static nextstep.courses.domain.image.ImageFileSize.MAX_FILE_SIZE;
import static nextstep.courses.domain.image.ImageSize.MIN_HEIGHT;
import static nextstep.courses.domain.image.ImageSize.MIN_WIDTH;

public class SessionCoverImageFixture {

    private static final String EXTENSION = "gif";

    public static SessionCoverImage coverImage(long size) {
        return new SessionCoverImage(SESSION_ID, size, MIN_WIDTH, MIN_HEIGHT, EXTENSION);
    }

    public static SessionCoverImage coverImage(int width, int height) {
        return new SessionCoverImage(SESSION_ID, MAX_FILE_SIZE, width, height, EXTENSION);
    }

    public static SessionCoverImage coverImage(String extension) {
        return new SessionCoverImage(SESSION_ID, MAX_FILE_SIZE, MIN_WIDTH, MIN_HEIGHT, extension);
    }

    public static SessionCoverImage coverImage() {
        return new SessionCoverImage(SESSION_ID, MAX_FILE_SIZE, MIN_WIDTH, MIN_HEIGHT, EXTENSION);
    }

}
