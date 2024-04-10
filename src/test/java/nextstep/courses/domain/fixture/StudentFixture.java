package nextstep.courses.domain.fixture;

import nextstep.courses.domain.session.Student;
import nextstep.courses.domain.session.Students;

import java.util.List;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;
import static nextstep.courses.domain.fixture.IdFixture.STUDENT_ID;

public class StudentFixture {

    public static final String STUDENT_LOGIN_ID = "hellonayeon";
    public static final String STUDENT_PASSWORD = "****";
    public static final String STUDENT_NAME = "NaYeon Kwon";
    public static final String STUDENT_EMAIL = "hellonykwon@gmail.com";

    public static Student student() {
        return new Student(STUDENT_ID, STUDENT_LOGIN_ID, STUDENT_PASSWORD, STUDENT_NAME, STUDENT_EMAIL);
    }

    public static Student student(Long id) {
        return new Student(id, STUDENT_LOGIN_ID, STUDENT_PASSWORD, STUDENT_NAME, STUDENT_EMAIL);
    }

    public static Students students() {
        return new Students(SESSION_ID, List.of(student(0L), student(1L)));
    }

}
