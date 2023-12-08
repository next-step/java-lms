package nextstep.sessions.domain.data.type;

import java.util.Arrays;

import nextstep.sessions.domain.exception.SessionsException;

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
            .orElseThrow(() -> new SessionsException("일치하는 코드 값이 없습니다."));
    }

    public String code() {
        return code;
    }

    public boolean isApproved() {
        return this == APPROVAL;
    }
}
