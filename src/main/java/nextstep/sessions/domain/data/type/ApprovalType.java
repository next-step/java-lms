package nextstep.sessions.domain.data.type;

import java.util.Arrays;

public enum ApprovalType {

    APPROVAL("Y", "승인"),
    BEFORE_APPROVAL("N", "미승인");

    private final String code;
    private final String title;

    ApprovalType(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public static ApprovalType valueOfCode(String code) {
        return Arrays.stream(values())
            .filter(v -> v.code.equals(code))
            .findFirst()
            .orElse(null);
    }

    public String code() {
        return code;
    }
}
