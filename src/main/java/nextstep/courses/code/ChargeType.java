package nextstep.courses.code;

import java.util.Arrays;
import java.util.function.Predicate;

public enum ChargeType {


    FREE(price -> price == 0L, "무료 강의는 가격이 0이어야 합니다"),
    PAID(price -> price > 0L, "유료 강의는 가격이 0보다 커야 합니다");

    private final Predicate<Long> priceValidator;
    private final String errorMessage;

    ChargeType(Predicate<Long> priceValidator, String errorMessage) {
        this.priceValidator = priceValidator;
        this.errorMessage = errorMessage;
    }

    public static ChargeType of(Long price) {
        return Arrays.stream(values())
                .filter(chargeType -> chargeType.priceValidator.test(price))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("가격은 음수일 수 없습니다."));
    }

    public void validateChargeType(Long price) {
        if (priceValidator.test(price)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
