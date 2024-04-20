package nextstep.courses.domain.fixture;

import nextstep.courses.domain.student.SessionStudent;
import nextstep.courses.domain.student.StudentEnrollmentStatus;

import static java.time.LocalDateTime.now;

public class SessionStudentFixture {

    public static SessionStudent student(Long sessionId, Long nsUserId) {
        return new SessionStudent(sessionId, nsUserId);
    }

    public static SessionStudent student(Long id, Long sessionId, Long nsUserId, StudentEnrollmentStatus enrollmentStatus) {
        return new SessionStudent(id, sessionId, nsUserId, enrollmentStatus.get(), now(), null);
    }
}
