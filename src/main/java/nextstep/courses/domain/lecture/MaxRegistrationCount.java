package nextstep.courses.domain.lecture;

import java.util.Comparator;
import nextstep.courses.error.exception.MaxRegistrationCountNotZero;

public class MaxRegistrationCount {

    private final RegistrationCount registrationCount;

    private static final Comparator<RegistrationCount> COMPARATOR = Comparator.comparingInt(
        RegistrationCount::getValue);

    public MaxRegistrationCount(RegistrationCount registrationCount) {
        if (registrationCount.isValueZero()){
            throw new MaxRegistrationCountNotZero(registrationCount);
        }

        this.registrationCount = registrationCount;
    }

    public boolean isMaxRegistrationCountOver(RegistrationCount other) {
        return COMPARATOR.compare(registrationCount, other) > 0;
    }

    public int getRegistrationCount() {
        return registrationCount.getValue();
    }
}
