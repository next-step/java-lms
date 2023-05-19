package nextstep.courses.domain;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SessionBillType {
    FREE("무료"),
    PAID("유료")
    ;

    private final String name;

    SessionBillType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static final Map<String, SessionBillType> SESSION_BILL_TYPE_MAP
            = Collections.unmodifiableMap(Stream.of(values())
                                                .collect(Collectors.toMap(SessionBillType::name, x -> x)));

    public static SessionBillType find(String name) {
        if (SESSION_BILL_TYPE_MAP.containsKey(name)) {
            return SESSION_BILL_TYPE_MAP.get(name);
        }

        throw new IllegalArgumentException(name + "을 찾을 수 없습니다.");
    }
}
