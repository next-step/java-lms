package nextstep.courses.domain.session;

import static java.util.Objects.isNull;
import static nextstep.courses.ExceptionMessage.INVALID_MAX_NUMBER_OF_ENROLLMENT_AND_FEE;

public class Fee {
    private final Long fee;

    public Fee() {
        this(0L);
    }

    public Fee(Long fee) {
        validateAmountInput(fee);
        this.fee = fee;
    }

    private void validateAmountInput(Long fee) {
        if (isNull(fee) || fee < 0) {
            throw new IllegalArgumentException(INVALID_MAX_NUMBER_OF_ENROLLMENT_AND_FEE.message());
        }
    }

    public boolean isFree() {
        return fee == 0;
    }

    public Long getFee() {
        return fee;
    }
}
