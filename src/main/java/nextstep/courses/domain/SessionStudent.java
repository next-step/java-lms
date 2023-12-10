package nextstep.courses.domain;

import nextstep.courses.enumeration.SessionStudentStatus;

import java.time.LocalDateTime;

import static nextstep.courses.enumeration.SessionStudentStatus.APPROVED;
import static nextstep.courses.enumeration.SessionStudentStatus.CANCELED;

public class SessionStudent extends BaseEntity {

    private Long id;
    private Long sessionId;
    private Long nsUserId;
    private SessionStudentStatus sessionStudentStatus;

    private SessionStudent(Long id, Long sessionId, Long nsUserId, SessionStudentStatus sessionStudentStatus) {
        super(LocalDateTime.now(), LocalDateTime.now());
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.sessionStudentStatus = sessionStudentStatus;
    }

    public static SessionStudent of(Long id, Long sessionId, Long nsUserId, SessionStudentStatus sessionStudentStatus) {
        return new SessionStudent(id, sessionId, nsUserId, sessionStudentStatus);
    }

    public void approve() {
        this.sessionStudentStatus = APPROVED;
    }

    public void canceled() {
        this.sessionStudentStatus = CANCELED;
    }

    public SessionStudentStatus getSessionStudentStatus() {
        return this.sessionStudentStatus;
    }
}
