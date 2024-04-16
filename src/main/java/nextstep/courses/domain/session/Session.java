package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.Users;

public abstract class Session {
    public static final String SESSION_NOT_OPENED = "강의 신청 가능 기한이 아닙니다.";
    public static final String ENROLLMENT_ALREADY_DONE = "이미 수강 신청을 완료하신 강의입니다.";
    public static final String PAYMENT_NOT_MATCHING = "결제 금액과 수강료가 일치해야 합니다.";

    protected final Long sessionId;
    private final SessionPeriod sessionPeriod;
    private final CoverImage coverImage;
    private final SessionStatusEnum sessionStatus;
    protected int maxEnrollments;
    protected Long fee;
    protected final Users users = new Users();

    protected Session(Long sessionId, SessionPeriod sessionPeriod,
                   CoverImage coverImage, SessionStatusEnum sessionStatus) {
        this.sessionId = sessionId;
        this.sessionPeriod = sessionPeriod;
        this.coverImage = coverImage;
        this.sessionStatus = sessionStatus;
    }

    protected boolean isSessionOpened() {
        return sessionStatus.isSessionOpened();
    }

    public Long getSessionId() {
        return sessionId;
    }

    public SessionPeriod getSessionPeriod() {
        return sessionPeriod;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public SessionStatusEnum getSessionStatus() {
        return sessionStatus;
    }

    public int getMaxEnrollments() {
        return maxEnrollments;
    }

    public Long getFee() {
        return fee;
    }

    public abstract void enrollStudent(NsUser user, Payment payment);

    public boolean hasStudentOf(NsUser user) {
        return users.hasUserOf(user);
    }

}
