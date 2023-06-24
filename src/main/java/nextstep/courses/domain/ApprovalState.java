package nextstep.courses.domain;

import java.util.Arrays;

public enum ApprovalState {
    UN_APPROVAL("UN_APPROVAL"),APPROVAL("APPROVAL");

    private final String code;

    ApprovalState(String code) {
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

    public static boolean isApproved(ApprovalState approvalState){
        return approvalState.getCode().equals(APPROVAL.getCode());
    }
}
