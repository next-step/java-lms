package nextstep.courses.record;

import nextstep.courses.domain.session.Enrollment;
import nextstep.courses.domain.session.constant.EnrollmentStatus;
import nextstep.courses.domain.session.constant.SelectionStatus;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class EnrollmentRecord {

    private Long id;
    private Long userId;
    private Long sessionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String selectionStatus;
    private String enrollmentStatus;

    public EnrollmentRecord(Long id, Long userId, Long sessionId) {
        this.id = id;
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public Enrollment toEnrollment(NsUser user) {
        return new Enrollment(this.id, user, this.sessionId,
                this.createdAt, this.updatedAt,
                this.selectionStatus, this.enrollmentStatus);
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public String getSelectionStatus() {
        return selectionStatus;
    }

    public String getEnrollmentStatus() {
        return enrollmentStatus;
    }
}
