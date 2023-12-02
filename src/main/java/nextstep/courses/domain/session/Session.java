package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private SessionMakingData sessionMakingData;
    private SessionStudent sessionStudent;
    private SessionStatus status;

    public Session(final String title, final long price, final LocalDateTime startDate, final LocalDateTime endDate) {
        this(new SessionInfo(title, price), new SessionDate(startDate, endDate), null);
    }

    public Session(final String title, final long price, final LocalDateTime startDate, final LocalDateTime endDate, final CoverImage coverImage) {
        this(new SessionInfo(title, price), new SessionDate(startDate, endDate), coverImage);
    }

    public Session(final String title, final LocalDateTime startDate, final LocalDateTime endDate) {
        this(new SessionInfo(title, 0L), new SessionDate(startDate, endDate), null);
    }

    public Session(final String title, final LocalDateTime startDate, final LocalDateTime endDate, final CoverImage coverImage) {
        this(new SessionInfo(title, 0L), new SessionDate(startDate, endDate), coverImage);
    }

    public Session(final SessionInfo sessionInfo, final SessionDate sessionDate, CoverImage coverImage) {
        this.sessionMakingData = new SessionMakingData(sessionInfo, sessionDate, coverImage);

        this.status = SessionStatus.READY;
        this.sessionStudent = new SessionStudent(15);
    }

    public long getPrice() {
        return this.sessionMakingData.getPrice();
    }

    public int getCurrentStudentCount() {
        return this.sessionStudent.getCurrentStudentCount();
    }

    public boolean isReachedMaxStudentLimit() {
        return this.sessionStudent.isReachedMaxStudentLimit();
    }

    public void increaseEnrollment(final NsUser user) {
        this.sessionStudent.increaseStudentCount(user);
    }

    private void setStatus(SessionStatus status) {
        this.status = status;
    }

    public boolean isNotRecruiting() {
        return !this.status.isRecruiting();
    }

    public CoverImage getCoverImage() {
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

    public void enroll(Payment payment, NsUser user) {
        if (isNotRecruiting()) {
            throw new IllegalStateException("session is not recruiting");
        }

        if (isPaidSession()) {
            validateIfPaidSession(payment);
        }

        increaseEnrollment(user);
    }

    private void validateIfPaidSession(final Payment payment) {
        if (payment.isNotPaid(getPrice())) {
            throw new IllegalStateException("paid amount is different with price");
        }

        if (isReachedMaxStudentLimit()) {
            throw new IllegalStateException("max student limit is reached");
        }
    }
}
