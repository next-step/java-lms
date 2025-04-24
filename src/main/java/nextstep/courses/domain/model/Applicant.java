package nextstep.courses.domain.model;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Applicant extends BaseEntity {
    private final Session session;
    private final NsUser user;
    private final Payment payment;
    private ApplicantStatus status;

    public Applicant(NsUser user, Session session, Payment payment) {
        this(null, session, user, payment, ApplicantStatus.APPLIED, LocalDateTime.now(), LocalDateTime.now());
    }

    public Applicant(Long id, Session session, NsUser user, Payment payment, ApplicantStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.session = session;
        this.user = user;
        this.payment = payment;
        this.status = status;
    }

    public boolean isEqualTo(NsUser user) {
        return this.user.equals(user);
    }

    public boolean isSameStatus(ApplicantStatus applicantStatus) {
        return this.status == applicantStatus;
    }

    public void makeStatus(ApplicantStatus applicantStatus) {
        this.status = applicantStatus;
    }

    public NsUser getNsUser() {
        return user;
    }

    public Session getSession() {
        return session;
    }

    public ApplicantStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                ", user=" + user +
                ", payment=" + payment +
                ", status=" + status +
                '}';
    }
}
