package nextstep.courses.domain;

import nextstep.courses.domain.vo.Price;

public class FreeStrategy implements ChargeStrategy {

    @Override
    public boolean isPaid(Price price) {
        return true;
    }
}
