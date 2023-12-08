package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;

public abstract class Session {
    public static final String NO_AVAILABLE_STATE_MESSAGE = "강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.";
    public static final String NO_AVAILABLE_SEATS_MESSAGE = "강의 수강 인원이 찼습니다";

    private final Long id;
    private final String name;
    private final SessionPeriod sessionPeriod;
    private final SessionState SessionState;
    private final CoverImage coverImage;

    public Session(Long id, String name, SessionPeriod sessionPeriod, nextstep.courses.domain.session.SessionState sessionState, CoverImage coverImage) {
        this.id = id;
        this.name = name;
        this.sessionPeriod = sessionPeriod;
        this.SessionState = sessionState;
        this.coverImage = coverImage;
    }

    protected void validateSessionState() {
        if (SessionState != nextstep.courses.domain.session.SessionState.RECRUITING) {
            throw new IllegalArgumentException(NO_AVAILABLE_STATE_MESSAGE);
        }
    }

    public Long id() {
        return this.id;
    }

    public abstract void enroll(final Payment payment);
}
