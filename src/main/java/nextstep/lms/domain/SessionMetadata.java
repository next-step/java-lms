package nextstep.lms.domain;

import nextstep.lms.enums.SessionProgressEnum;

public class SessionMetadata {
    private final CoverImage coverImage;
    private final SessionProgressEnum sessionProgressEnum;

    public SessionMetadata(CoverImage coverImage, SessionProgressEnum sessionProgressEnum) {
        this.coverImage = coverImage;
        this.sessionProgressEnum = sessionProgressEnum;
    }

    public void sessionStatusCheck() {
        if (sessionProgressEnum != SessionProgressEnum.PROGRESSING) {
            throw new IllegalArgumentException("모집중이 아닙니다.");
        }
    }
}
