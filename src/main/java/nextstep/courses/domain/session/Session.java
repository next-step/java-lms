package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

public abstract class Session {
    private final SessionInfo sessionInfo;
    private final SessionDate sessionDate;
    private final SessionStudent sessionStudent;
    private SessionStatus status;

    protected Session(final String title, final long price, final LocalDateTime startDate, final LocalDateTime endDate) {
        this(new SessionInfo(title, price), new SessionDate(startDate, endDate));
    }

    protected Session(final String title, final LocalDateTime startDate, final LocalDateTime endDate) {
        this(new SessionInfo(title, 0), new SessionDate(startDate, endDate));
    }

    protected Session(final SessionInfo sessionInfo, final SessionDate sessionDate) {
        validateSession(sessionInfo, sessionDate);

        this.sessionInfo = sessionInfo;
        this.sessionDate = sessionDate;
        this.status = SessionStatus.READY;
        this.sessionStudent = new SessionStudent(15, 0);
    }

    private void validateSession(final SessionInfo sessionInfo, final SessionDate sessionDate) {
        Assert.notNull(sessionInfo, "session info cannot be null");
        Assert.notNull(sessionDate, "session date cannot be null");
    }

    protected long getPrice() {
        return this.sessionInfo.getPrice();
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

    protected void setStatus(SessionStatus status) {
        this.status = status;
    }

    protected boolean isNotRecruiting() {
        return this.status != SessionStatus.RECRUITING;
    }

    public abstract void ready();

    public abstract void recruit();

    public abstract void close();

    public abstract void enroll(Payment payment);
}
