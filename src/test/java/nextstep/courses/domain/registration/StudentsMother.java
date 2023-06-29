package nextstep.courses.domain.registration;

import static nextstep.courses.domain.registration.StudentMother.aStudent;
import static nextstep.courses.domain.registration.StudentsBuilder.aStudentsBuilder;

public class StudentsMother {
    public static StudentsBuilder aStudents() {
        return aStudentsBuilder()
                .withMaxUserCount(1)
                .withStudent(aStudent().build());
    }

}
