package nextstep.courses.domain.code;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.Students;
import nextstep.courses.domain.strategy.EnrollmentStrategy;
import nextstep.courses.domain.strategy.FreeEnrollmentStrategy;
import nextstep.courses.domain.strategy.PaidEnrollmentStrategy;

public enum EnrollmentType {

    FREE("무료 강의", new FreeEnrollmentStrategy()),
    PAID("유료 강의", new PaidEnrollmentStrategy());

    private final String description;
    private final EnrollmentStrategy enrollmentStrategy;

    EnrollmentType(String description,
                   EnrollmentStrategy enrollmentStrategy) {
        this.description = description;
        this.enrollmentStrategy = enrollmentStrategy;
    }

    public String getDescription() {
        return this.description;
    }

    public void validateEnroll(long payment,
                               Amount amount,
                               int capacity,
                               Students students) {
        enrollmentStrategy.validate(payment, amount, capacity, students);
    }
}
