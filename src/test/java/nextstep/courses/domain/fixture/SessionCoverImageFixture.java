package nextstep.courses.domain.fixture;

import nextstep.courses.domain.session.SessionCoverImage;
import nextstep.courses.exception.SessionException;

import static nextstep.courses.domain.session.SessionCoverImage.*;

public class SessionCoverImageFixture {

    private static final String EXTENSION = "gif";

    public static SessionCoverImage coverImage(long size) throws SessionException {
        return new SessionCoverImage(size, MIN_WIDTH, MIN_HEIGHT, EXTENSION);
    }

    public static SessionCoverImage coverImage(int width, int height) throws SessionException {
        return new SessionCoverImage(MAX_FILE_SIZE, width, height, EXTENSION);
    }

    public static SessionCoverImage coverImage(String extension) throws SessionException {
        return new SessionCoverImage(MAX_FILE_SIZE, MIN_WIDTH, MIN_HEIGHT, extension);
    }

    public static SessionCoverImage coverImage() throws SessionException {
        return new SessionCoverImage(MAX_FILE_SIZE, MIN_WIDTH, MIN_HEIGHT, EXTENSION);
    }

}
