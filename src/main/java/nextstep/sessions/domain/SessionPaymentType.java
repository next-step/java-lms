package nextstep.sessions.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum SessionPaymentType {
    FREE("무료"),
    PAID("유료");

    private static final Map<String, SessionPaymentType> VALUE_MAP
            = Arrays.stream(SessionPaymentType.values())
            .collect(Collectors.toUnmodifiableMap(SessionPaymentType::getName, o -> o));

    private final String name;

    SessionPaymentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static SessionPaymentType find(String name) {
        if (VALUE_MAP.containsKey(name)) {
            return VALUE_MAP.get(name);
        }
        throw new IllegalArgumentException("유요하지 않은 지불유형입니다.(" + name + ")");
    }

}
