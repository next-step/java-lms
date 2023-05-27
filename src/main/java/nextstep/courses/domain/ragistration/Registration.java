package nextstep.courses.domain.ragistration;

import nextstep.common.BaseEntity;

import java.time.LocalDateTime;

public class Registration extends BaseEntity {
    private Long courseId;
    private Long sessionId;
    private Long nsUserId;
    private RegistrationType registrationType;

    public static Registration receipt(Long courseId, Long sessionId, Long nsUserId) {
        return new Registration(null, courseId, sessionId, nsUserId, RegistrationType.RECEIPT, LocalDateTime.now(), null);
    }

    public Registration(Long id, Long courseId, Long sessionId, Long nsUserId, RegistrationType registrationType, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.courseId = courseId;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.registrationType = registrationType;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public RegistrationType getRegistrationType() {
        return registrationType;
    }

    public boolean isReceipt() {
        return this.registrationType == RegistrationType.RECEIPT;
    }

    public void registration() {
        this.registrationType = RegistrationType.APPROVAL;
    }

    public void cancelRegistration() {
        this.registrationType = RegistrationType.REFUSE;
    }
}
