package nextstep.courses.domain.session.enrollment.count;

import nextstep.courses.domain.session.enrollment.count.RegistrationCount;
import nextstep.courses.error.exception.MaxRegistrationCountNotZero;

public class MaxRegistrationCount {

    private final int value;

    public MaxRegistrationCount(int value) {
        if (value == 0) {
            throw new MaxRegistrationCountNotZero(value);
        }

        this.value = value;
    }

    public boolean isCountNotOver(RegistrationCount registrationCount) {
        return value > registrationCount.getValue();
    }

    public int getValue() {
        return value;
    }
}
