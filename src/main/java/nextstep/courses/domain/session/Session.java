package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public abstract class Session {
    private SessionMakingData sessionMakingData;
    private SessionStudent sessionStudent;
    private SessionStatus status;

    protected Session(final String title, final long price, final LocalDateTime startDate, final LocalDateTime endDate) {
        this(new SessionInfo(title, price), new SessionDate(startDate, endDate), null);
    }

    protected Session(final String title, final long price, final LocalDateTime startDate, final LocalDateTime endDate, final CoverImage coverImage) {
        this(new SessionInfo(title, price), new SessionDate(startDate, endDate), coverImage);
    }

    protected Session(final String title, final LocalDateTime startDate, final LocalDateTime endDate) {
        this(new SessionInfo(title, 0L), new SessionDate(startDate, endDate), null);
    }

    protected Session(final String title, final LocalDateTime startDate, final LocalDateTime endDate, final CoverImage coverImage) {
        this(new SessionInfo(title, 0L), new SessionDate(startDate, endDate), coverImage);
    }

    protected Session(final SessionInfo sessionInfo, final SessionDate sessionDate, CoverImage coverImage) {
        this.sessionMakingData = new SessionMakingData(sessionInfo, sessionDate, coverImage);

        this.status = SessionStatus.READY;
        this.sessionStudent = new SessionStudent(15, 0);
    }

    public static Session of(final String title, final long price, final LocalDateTime startDate, final LocalDateTime endDate) {
        if (price == 0) {
            return new FreeSession(title, startDate, endDate);
        }

        return new PaidSession(title, price, startDate, endDate);
    }

    public static Session of(final String title, final long price, final LocalDateTime startDate, final LocalDateTime endDate, final CoverImage coverImage) {
        if (price == 0) {
            return new FreeSession(title, startDate, endDate, coverImage);
        }

        return new PaidSession(title, price, startDate, endDate, coverImage);
    }

    protected long getPrice() {
        return this.sessionMakingData.getPrice();
    }

    protected int getCurrentStudentCount() {
        return this.sessionStudent.getCurrentStudentCount();
    }

    protected boolean isReachedMaxStudentLimit() {
        return this.sessionStudent.isReachedMaxStudentLimit();
    }

    protected void increaseEnrollment() {
        this.sessionStudent.increaseStudentCount();
    }

    private void setStatus(SessionStatus status) {
        this.status = status;
    }

    protected boolean isNotRecruiting() {
        return !this.status.isRecruiting();
    }

    protected CoverImage getCoverImage() {
        return this.sessionMakingData.getCoverImage();
    }

    public void ready() {
        setStatus(SessionStatus.READY);
    }

    public void recruit() {
        setStatus(SessionStatus.RECRUITING);
    }

    public void close() {
        setStatus(SessionStatus.CLOSED);
    }

    private boolean isPaidSession() {
        return this.sessionMakingData.isPaidSession();
    }

    public abstract void enroll(Payment payment);
}
