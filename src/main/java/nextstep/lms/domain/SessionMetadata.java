package nextstep.lms.domain;

import nextstep.lms.enums.SessionStatusEnum;

public class SessionMetadata {
    private final CoverImage coverImage;
    private final SessionStatusEnum sessionStatusEnum;

    public SessionMetadata(CoverImage coverImage, SessionStatusEnum sessionStatusEnum) {
        this.coverImage = coverImage;
        this.sessionStatusEnum = sessionStatusEnum;
    }

    public void sessionStatusCheck() {
        if (sessionStatusEnum != SessionStatusEnum.RECRUITING) {
            throw new IllegalArgumentException("모집중이 아닙니다.");
        }
    }
}
