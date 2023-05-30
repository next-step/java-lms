package nextstep.courses.domain;


import java.util.Arrays;

public enum ChargeType {
    CHARGED,
    FREE;

    public static ChargeType find(String name) {
        return Arrays.stream(ChargeType.values())
                .filter(chargeType -> chargeType.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 요금 유형 입니다."));
    }
}
