package nextstep.courses.domain;

import nextstep.courses.domain.enums.ApprovalState;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Enrollment {
    private final long sessionId;
    private final NsUser student;
    private String enrollDate;
    private ApprovalState approvalState;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Enrollment(long sessionId, NsUser nsUser) {
        this(sessionId, nsUser, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")), ApprovalState.PENDING,
                LocalDateTime.now(), null);
    }

    public Enrollment(long sessionId, NsUser nsUser, String enrollDate, ApprovalState approvalState,
                      LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.sessionId = sessionId;
        this.student = nsUser;
        this.enrollDate = enrollDate;
        this.approvalState = approvalState;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object other) {
        return this.student.equals(((Enrollment) other).student) &&
                this.sessionId == ((Enrollment) other).sessionId;
    }

    public NsUser getStudent() {
        return student;
    }

    public long getSessionId() {
        return sessionId;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public int getApprovalState() {
        return this.approvalState.getInt();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void approve() {
        this.approvalState = ApprovalState.APPROVED;
    }

    public boolean isApproved() {
        return this.approvalState.isApproved();
    }

    public boolean isSameSession(long sessionId) {
        return this.sessionId == sessionId;
    }

    public void cancel() {
        this.approvalState = ApprovalState.CANCELED;
    }
}
