package nextstep.courses.domain;

import nextstep.courses.exception.BusinessInvalidValueException;

public class Price {
    private final long price;

    public Price(long price) {
        this.price = price;
    }

    public void validatePrice(long price) {
        if (this.price != price) {
            throw new BusinessInvalidValueException("강의 가격이 변동되었습니다.");
        }
    }

    public long price() {
        return price;
    }
}
