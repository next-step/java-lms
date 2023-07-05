package nextstep.courses.domain;

import java.util.Arrays;
import java.util.EnumSet;

public enum SessionState {
    PREPARING("PREPARING"), PROCEEDING("PROCEEDING"), CLOSE("CLOSE");

    static EnumSet<SessionRecruitState> OldVersion = EnumSet.of(SessionRecruitState.RECRUITING);

    private final String code;

    SessionState(String code) {
        this.code = code;
    }

    public static SessionState convert(String code) {

        if(OldVersion.stream()
                .anyMatch(s -> s.getCode().equals(code))){
            return PREPARING;
        }

        return Arrays.stream(SessionState.values())
                .filter(s -> s.code.equals(code))
                .findFirst()
                .orElse(CLOSE);
    }

    public boolean isProceeding() {
        return this == PROCEEDING;
    }

    public boolean isPreparing() {
        return this == PREPARING;
    }
}
