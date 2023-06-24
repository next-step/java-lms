package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionRecruitState {
    UN_RECRUITING("UN_RECRUITING"), RECRUITING("RECRUITING");

    private final String code;

    SessionRecruitState(String code) {
        this.code = code;
    }

    public static SessionRecruitState convert(String code) {
        return Arrays.stream(SessionRecruitState.values())
                .filter(s -> s.code.equals(code))
                .findFirst()
                .orElse(UN_RECRUITING);
    }

    public String getCode() {
        return code;
    }

    public boolean isRecruitable() {
        return this.equals(RECRUITING);
    }
}
