package nextstep.courses.record;

import nextstep.courses.domain.session.Enrollment;
import nextstep.users.domain.NsUser;

public class EnrollmentRecord {

    private Long id;
    private Long userId;
    private Long sessionId;

    public EnrollmentRecord(Long id, Long userId, Long sessionId) {
        this.id = id;
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public Enrollment toEnrollment(NsUser user) {
        return new Enrollment(this.id, user, this.sessionId);
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
}
