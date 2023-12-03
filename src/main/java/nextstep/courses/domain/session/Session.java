package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private SessionMakingData sessionMakingData;
    private SessionStudent sessionStudent;
    private SessionStatus status;
    private Enrollment enrollment;

    public Session(final String title, final long price, final LocalDateTime startDate, final LocalDateTime endDate) {
        this(new SessionInfo(title, price), new SessionDate(startDate, endDate), null);
    }

    public Session(final String title, final long price, final LocalDateTime startDate, final LocalDateTime endDate, final CoverImage coverImage) {
        this(new SessionInfo(title, price), new SessionDate(startDate, endDate), coverImage);
    }

    public Session(final String title, final LocalDateTime startDate, final LocalDateTime endDate) {
        this(new SessionInfo(title, 0L), new SessionDate(startDate, endDate), null);
    }

    public Session(final SessionInfo sessionInfo, final SessionDate sessionDate, CoverImage coverImage) {
        this.sessionMakingData = new SessionMakingData(sessionInfo, sessionDate, coverImage);

        this.status = SessionStatus.READY;
        this.sessionStudent = new SessionStudent(15);

        this.enrollment = new Enrollment(sessionInfo.getPrice());
    }

    public long getPrice() {
        return this.sessionMakingData.getPrice();
    }

    public int getCurrentStudentCount2() {
        return this.enrollment.getCurrentStudentCount();
    }

    private void setStatus2(SessionStatus status) {
        enrollment.setStatus(status);
    }

    public boolean isNotRecruiting() {
        return !this.status.isRecruiting();
    }

    public CoverImage getCoverImage() {
        return this.sessionMakingData.getCoverImage();
    }

    public void ready() {
        setStatus2(SessionStatus.READY);
    }

    public void recruit() {
        setStatus2(SessionStatus.RECRUITING);
    }

    public void close() {
        setStatus2(SessionStatus.CLOSED);
    }

    public void enroll2(Payment payment, NsUser user) {
        enrollment.enroll(payment, user);
    }
}
