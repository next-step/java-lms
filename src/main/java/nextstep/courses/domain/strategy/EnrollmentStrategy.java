package nextstep.courses.domain.strategy;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.Students;

public interface EnrollmentStrategy {
    void validate(long payment,
                  Amount amount,
                  int capacity,
                  Students students);
}
