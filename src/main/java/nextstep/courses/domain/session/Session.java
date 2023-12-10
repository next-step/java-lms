package nextstep.courses.domain.session;

import nextstep.common.domain.BaseEntity;
import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Session extends BaseEntity {
    private static final String NO_AVAILABLE_STATE_MESSAGE = "강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.";
    private static final String NO_AVAILABLE_SEATS_MESSAGE = "강의 수강 인원이 찼습니다";
    private static final String INVALID_FEE_MESSAGE = "결제한 금액과 강의의 금액이 다릅니다.";

    private final String title;
    private final SessionPeriod sessionPeriod;
    private final SessionState SessionState;
    private final CoverImage coverImage;

    private SessionFeeType sessionFeeType;
    private SessionFee sessionFee;
    private EnrollmentCount enrollmentCount;

    public Session(final String title,
                   final SessionPeriod sessionPeriod,
                   final SessionState sessionState,
                   final CoverImage coverImage,
                   final SessionFee sessionFee,
                   final EnrollmentCount enrollmentCount) {
        this(null, title, sessionPeriod, sessionState, coverImage, SessionFeeType.toSessionFeeType(sessionFee), sessionFee, enrollmentCount,
             LocalDateTime.now(), null);
    }

    public Session(final Long id,
                   final String title,
                   final SessionPeriod sessionPeriod,
                   final SessionState sessionState,
                   final CoverImage coverImage,
                   final SessionFeeType sessionFeeType,
                   final SessionFee sessionFee,
                   final EnrollmentCount enrollmentCount,
                   final LocalDateTime createAt,
                   LocalDateTime updatedAt) {
        super(id, createAt, updatedAt);
        this.id = id;
        this.title = title;
        this.sessionPeriod = sessionPeriod;
        this.SessionState = sessionState;
        this.coverImage = coverImage;
        this.sessionFeeType = sessionFeeType;
        this.sessionFee = sessionFee;
        this.enrollmentCount = enrollmentCount;
    }

    public void validateSessionState() {
        if (SessionState != nextstep.courses.domain.session.SessionState.RECRUITING) {
            throw new IllegalArgumentException(NO_AVAILABLE_STATE_MESSAGE);
        }
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public SessionPeriod getSessionPeriod() {
        return sessionPeriod;
    }

    public SessionState getSessionState() {
        return SessionState;
    }

    public long getCoverImageId() {
        return coverImage.getId();
    }

    public SessionFeeType getSessionFeeType() {
        return sessionFeeType;
    }

    public SessionFee getSessionFee() {
        return sessionFee;
    }

    public EnrollmentCount getEnrollmentCount() {
        return enrollmentCount;
    }

    public void validateEnrollState(Payment payment) {
        validateSessionState();
        if (sessionFee.isFree()) {
            return;
        }
        if (enrollmentCount.isNoRemaining()) {
            throw new IllegalArgumentException(NO_AVAILABLE_SEATS_MESSAGE);
        }
        if (sessionFee.isNotEqualTo(payment)) {
            throw new IllegalArgumentException(INVALID_FEE_MESSAGE);
        }
        enrollmentCount.decrease();
    }
}
