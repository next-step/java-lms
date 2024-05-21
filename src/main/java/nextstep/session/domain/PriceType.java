package nextstep.session.domain;

import java.util.Arrays;
import nextstep.session.SessionStatusInvalidException;

public enum PriceType {
    FREE, PAID;

    public boolean isFree() {
        return this == FREE;
    }

    public static PriceType convert(String priceType) {
        return Arrays.stream(values())
            .filter(price -> price.name().equalsIgnoreCase(priceType))
            .findFirst()
            .orElseThrow(() -> new SessionStatusInvalidException("존재하지 않는 타입입니다."));
    }
}
