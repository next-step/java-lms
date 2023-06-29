package nextstep.courses.domain.registration;

import nextstep.users.domain.NsUserTest;

import static nextstep.courses.domain.registration.StudentBuilder.aStudentBuilder;

public class StudentMother {
    public static StudentBuilder aStudent() {
        return aStudentBuilder()
                .withSessionId(1L)
                .withUserId(NsUserTest.JAVAJIGI.getId());
    }

    public static StudentBuilder anotherStudent() {
        return aStudentBuilder()
                .withSessionId(1L)
                .withUserId(NsUserTest.SANJIGI.getId());
    }
}
