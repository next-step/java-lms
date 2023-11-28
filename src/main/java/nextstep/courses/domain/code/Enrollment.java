package nextstep.courses.domain.code;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.Students;
import nextstep.courses.domain.strategy.EnrollmentStrategy;
import nextstep.courses.domain.strategy.FreeEnrollmentStrategy;
import nextstep.courses.domain.strategy.PaidEnrollmentStrategy;

public enum Enrollment {

    FREE("무료 강의", new FreeEnrollmentStrategy()),
    PAID("유료 강의", new PaidEnrollmentStrategy());

    private final String description;
    private final EnrollmentStrategy enrollmentStrategy;

    Enrollment(String description,
               EnrollmentStrategy enrollmentStrategy) {
        this.description = description;
        this.enrollmentStrategy = enrollmentStrategy;
    }

    public String getDescription() {
        return this.description;
    }

    public void enroll(long payment,
                       Amount amount,
                       int capacity,
                       Student student,
                       Students students) {
        enrollmentStrategy.validate(payment, amount, capacity, students);
        students.enroll(student);
    }
}
