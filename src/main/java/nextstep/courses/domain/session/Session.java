package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;

public abstract class Session {
    public static final String NOT_REMAIN_MSG = "남은 자리가 없습니다.";
    public static final String NOT_RECRUITING_MSG = "강의 모집중인 강의가 아닙니다.";
    private final Long id;
    private final String title;
    private final SessionPeriod sessionPeriod;
    private final SessionStatus sessionStatus;
    private final CoverImage coverImage;

    public Session(final Long id, final String title, final SessionPeriod sessionPeriod,
                   final SessionStatus sessionStatus, final CoverImage coverImage) {
        this.id = id;
        this.title = title;
        this.sessionPeriod = sessionPeriod;
        this.sessionStatus = sessionStatus;
        this.coverImage = coverImage;
    }
    protected void checkRecruiting() {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalArgumentException(NOT_RECRUITING_MSG);
        }
    }
    public Long id() {
        return this.id;
    }
    public abstract void enroll(final Payment payment);
}
