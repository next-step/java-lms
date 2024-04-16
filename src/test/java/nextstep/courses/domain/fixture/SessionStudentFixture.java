package nextstep.courses.domain.fixture;

import nextstep.courses.domain.student.SessionStudent;

public class SessionStudentFixture {

    public static SessionStudent student(Long sessionId, Long nsUserId) {
        return new SessionStudent(sessionId, nsUserId);
    }

}
