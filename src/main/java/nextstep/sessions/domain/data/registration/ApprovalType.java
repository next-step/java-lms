package nextstep.sessions.domain.data.registration;

import java.util.Arrays;

import nextstep.sessions.domain.exception.CannotApproveRegistrationException;

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
            .filter(approvalType -> approvalType.code.equals(code))
            .findFirst()
            .orElseThrow(() -> new CannotApproveRegistrationException("유효하지 않은 승인 코드 입니다."));
    }

    public String code() {
        return code;
    }

    public boolean isApproved() {
        return this == APPROVAL;
    }
}
