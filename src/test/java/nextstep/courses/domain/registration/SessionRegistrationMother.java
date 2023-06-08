package nextstep.courses.domain.registration;

import static nextstep.courses.domain.registration.SessionRegistrationBuilder.*;
import static nextstep.courses.domain.registration.StudentBuilder.aStudentBuilder;
import static nextstep.courses.domain.registration.StudentMother.aStudent;
import static nextstep.courses.domain.registration.StudentsBuilder.aStudentsBuilder;

public class SessionRegistrationMother {

    public static SessionRegistrationBuilder aSessionRegistration() {
        return aSessionRegistrationBuilder()
                .withSessionStatus(SessionStatus.PROGRESSING)
                .withSessionRecruitmentStatus(SessionRecruitmentStatus.RECRUITING)
                .withStudents(aStudentsBuilder()
                        .withMaxUserCount(1)
                        .withStudent(aStudent()
                                .build())
                        .build());
    }
}
