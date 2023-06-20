package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionState {
    PREPARING("PREPARING"), RECRUITING("RECRUITING"), CLOSE("CLOSE");

    private final String code;

    SessionState(String code) {
        this.code = code;
    }

    public static SessionState convert(String code) {
        return Arrays.stream(SessionState.values())
                .filter(s -> s.code.equals(code))
                .findFirst()
                .orElse(CLOSE);
    }

    public boolean isRecruitable() {
        return this.equals(RECRUITING);
    }
}
