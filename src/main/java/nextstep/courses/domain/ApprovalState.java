package nextstep.courses.domain;

import java.util.Arrays;

public enum ApprovalState {
    UN_APPROVAL("UN_APPROVAL"),APPROVAL("APPROVAL");

    private final String code;

    private ApprovalState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ApprovalState convert(String code) {
        return Arrays.stream(ApprovalState.values())
                .filter(s -> s.code.equals(code))
                .findFirst()
                .orElse(UN_APPROVAL);
    }

    public boolean isApproved(){
        return this.getCode() == APPROVAL.getCode();
    }
}
