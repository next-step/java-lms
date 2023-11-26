package nextstep.courses.domain.strategy;

import nextstep.courses.domain.Student;
import nextstep.courses.domain.Students;

public interface EnrollmentStrategy {
    void enroll(long payment,
                Student student,
                Students students);
}
