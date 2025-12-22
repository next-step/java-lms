package nextstep.sessions.domain;

import nextstep.sessions.domain.image.SessionImage;

public class SessionInfo {

    static final String ERROR_COVER_IMAGE_REQUIRED = "강의 커버 이미지는 필수입니다";

    private final Period period;

    private SessionImage image;

    public SessionInfo(Period period, SessionImage image) {
        validateImage(image);
        this.period = period;
        this.image = image;
    }

    private static void validateImage(SessionImage image) {
        if (image == null) {
            throw new IllegalArgumentException(ERROR_COVER_IMAGE_REQUIRED);
        }
    }

}
