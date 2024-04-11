package nextstep.courses.domain.fixture;

import nextstep.courses.domain.session.Student;
import nextstep.courses.domain.session.Students;

import java.util.List;

import static nextstep.courses.domain.fixture.IdFixture.*;

public class StudentFixture {

    public static Student student() {
        return new Student(STUDENT_ID, NS_USER_ID);
    }

    public static Student student(Long id) {
        return new Student(id, NS_USER_ID);
    }

    public static Students students() {
        return new Students(SESSION_ID, List.of(student(0L), student(1L)));
    }

}
