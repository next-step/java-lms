package nextstep.courses.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class SessionFee {

    private BigDecimal fee;

    public SessionFee(BigDecimal fee) {
        this.fee = fee;
    }

    public boolean hasPaid(Long fee) {
        return Objects.equals(this.fee, new BigDecimal(fee));
    }


}
