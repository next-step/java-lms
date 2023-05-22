package nextstep.courses.domain;

import java.util.HashMap;
import java.util.Map;

public class SessionName {
    private static final Map<String, SessionName> sessionNameCache = new HashMap<>();

    private final String name;

    private SessionName(String name) {
        this.name = name;
    }

    public static SessionName of(String name) {
        validateName(name);
        sessionNameCache.putIfAbsent(name, new SessionName(name));
        return sessionNameCache.get(name);
    }

    public String name() {
        return name;
    }

    private static void validateName(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("세션 이름은 null이나 공백일 수 없습니다: " + name);
        }
    }
}
