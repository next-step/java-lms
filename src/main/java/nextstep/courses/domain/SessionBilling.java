package nextstep.courses.domain;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SessionBilling {
    FREE,
    PAID;

    private static final Map<String, SessionBilling> SESSION_BILL_TYPE_MAP
            = Collections.unmodifiableMap(Stream.of(values())
            .collect(Collectors.toMap(SessionBilling::name, x -> x)));

    public static SessionBilling find(String name) {
        if (SESSION_BILL_TYPE_MAP.containsKey(name)) {
            return SESSION_BILL_TYPE_MAP.get(name);
        }

        throw new IllegalArgumentException(name + "을 찾을 수 없습니다.");
    }

}
