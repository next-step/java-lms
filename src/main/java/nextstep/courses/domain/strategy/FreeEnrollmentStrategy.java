package nextstep.courses.domain.strategy;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.Students;

public class FreeEnrollmentStrategy implements EnrollmentStrategy {

    @Override
    public void enroll(long payment,
                       Amount amount,
                       int capacity,
                       Student student,
                       Students students) {
        students.enrol(student);
    }

}
