package nextstep.courses.domain;

import nextstep.courses.domain.vo.Price;

public interface ChargeStrategy {
    boolean isPaid(Price price);
}
