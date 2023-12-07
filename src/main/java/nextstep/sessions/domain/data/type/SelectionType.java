package nextstep.sessions.domain.data.type;

import java.util.Arrays;

public enum SelectionType {
    SELECTION("Y", "선발"),
    BEFORE_SELECTION("N", "미선발");

    private final String code;
    private final String title;

    SelectionType(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public static SelectionType valueOfCode(String code) {
        return Arrays.stream(values())
            .filter(v -> v.code.equals(code))
            .findFirst()
            .orElse(null);
    }

    public String code() {
        return code;
    }
}
