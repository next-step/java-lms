package nextstep.courses.domain.model;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Applicant extends BaseEntity {
    private final Long sessionId;
    private final NsUser user;
    private final Payment payment;
    private ApplicantStatus status;

    public Applicant(NsUser user, Long sessionId, Payment payment) {
        this(null, sessionId, user, payment, ApplicantStatus.APPLIED, LocalDateTime.now(), LocalDateTime.now());
    }

    public Applicant(Long id, Long sessionId, NsUser user, Payment payment, ApplicantStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.sessionId = sessionId;
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
        if (!status.canChangeTo(applicantStatus)) {
            throw new IllegalArgumentException("Applicant status not changeable from " + status.name() + " to " + applicantStatus.name());
        }
        this.status = applicantStatus;
    }

    public NsUser getNsUser() {
        return user;
    }


    public ApplicantStatus getStatus() {
        return status;
    }


    public Map<String, Object> getParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("session_id", sessionId);
        parameters.put("ns_user_id", user.getId());
        parameters.put("created_at", getCreatedAt());
        parameters.put("updated_at", getUpdatedAt());
        return parameters;
    }

    public Long getSessionId() {
        return sessionId;
    }
}
