package nextstep.courses.domain.session;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public abstract class Session extends BaseEntity {
    protected static final String NOT_REMAIN_MSG = "남은 자리가 없습니다.";
    protected static final String NOT_RECRUITING_MSG = "강의 모집중인 강의가 아닙니다.";
    private String title;
    private SessionPeriod sessionPeriod;
    private SessionStatus sessionStatus;
    private CoverImage coverImage;

    public Session(final String title, final SessionPeriod sessionPeriod,
                   final SessionStatus sessionStatus, final CoverImage coverImage) {
        this(null, title, sessionPeriod, sessionStatus, coverImage,  LocalDateTime.now(), null);
    }
    public Session(final Long id, final String title, final SessionPeriod sessionPeriod,
                   final SessionStatus sessionStatus, final CoverImage coverImage, final LocalDateTime createAt, LocalDateTime updatedAt) {
        super(id, createAt, updatedAt);
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
    public abstract void enroll(final Payment payment);

    public String title() {
        return title;
    }

    public SessionPeriod sessionPeriod() {
        return sessionPeriod;
    }

    public SessionStatus sessionStatus() {
        return sessionStatus;
    }

    public CoverImage coverImage() {
        return coverImage;
    }
}
