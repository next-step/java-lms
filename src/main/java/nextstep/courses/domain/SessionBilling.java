package nextstep.courses.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SessionBilling {
    FREE,
    PAID;

    public static SessionBilling find(String billingType) {
        return Arrays.stream(values())
                .filter(billing -> billing.name().equals(billingType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(billingType + "을 찾을 수 없습니다."));
    }

}
