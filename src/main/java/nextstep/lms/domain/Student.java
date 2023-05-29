package nextstep.lms.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Student {

    private Long nsUserId;
    private Long sessionId;
    private RegisterType registerType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Student(Long nsUserId, Long sessionId, RegisterType registerType) {
        this(nsUserId, sessionId, registerType, null, null);
    }

    public Student(Long nsUserId, Long sessionId, RegisterType registerType, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
        this.registerType = registerType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Student init(NsUser nsUser, Session session) {
        RegisterType registerType = RegisterType.NOT_PAID;
        if (session.isFree()) {
            registerType = RegisterType.REGISTERED;
        }

        return new Student(nsUser.getId(), session.getId(), registerType);
    }

    public void sessionCancel() {
        this.registerType = RegisterType.CANCELED;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public String getRegisterType() {
        return registerType.toString();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
