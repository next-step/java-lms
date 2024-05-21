package nextstep.session.domain;

import java.util.Arrays;
import nextstep.session.SessionStatusInvalidException;

public enum EnrollmentApprovalStatus {
    HOLD("대기"),
    APPROVED("승인"),
    CANCELLED("취소");

    private final String description;

    EnrollmentApprovalStatus(String description) {
        this.description = description;
    }

    public static EnrollmentApprovalStatus convert(String status) {
        return Arrays.stream(values())
            .filter(sessionType -> sessionType.description.equals(status))
            .findFirst()
            .orElseThrow(() -> new SessionStatusInvalidException("존재하지 않는 상태입니다."));
    }

    public String getDescription() {
        return description;
    }
}
