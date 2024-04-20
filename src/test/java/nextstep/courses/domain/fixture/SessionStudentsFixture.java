package nextstep.courses.domain.fixture;

import nextstep.courses.domain.student.SessionStudent;
import nextstep.courses.domain.student.SessionStudents;

import java.util.List;

public class SessionStudentsFixture {

    public static SessionStudents students(SessionStudent... students) {
        return new SessionStudents(List.of(students));
    }

    public static SessionStudents students(List<SessionStudent> students) {
        return new SessionStudents(students);
    }

}
