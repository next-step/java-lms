package nextstep.courses.domain.strategy;

import nextstep.courses.domain.Student;
import nextstep.courses.domain.Students;
import nextstep.courses.domain.code.EnrollmentType;

public class FreeEnrollmentStrategy implements EnrollmentStrategy {

    private final long id;
    private final long sessionId;
    private final EnrollmentType enrollmentType;

    public FreeEnrollmentStrategy(long id,
                                  long sessionId) {
        this.id = id;
        this.sessionId = sessionId;
        this.enrollmentType = EnrollmentType.FREE;
    }

    @Override
    public void enroll(long amount,
                       Student student,
                       Students students) {
        students.enrol(student);
    }

}
