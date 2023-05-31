package nextstep.courses.domain.enums;

import java.util.Arrays;

public enum ApprovalStatus {
    APPROVED("승인"),
    CANCELED("취소"),
    PENDING("대기중");

    private String description;

    ApprovalStatus(String description) {
        this.description = description;
    }

    public static ApprovalStatus of(String approvalStatus) {
        return Arrays.stream(values())
                .filter(a -> a.toString().equals(approvalStatus))
                .findFirst()
                .orElse(PENDING);
    }

    public boolean isApproved() {
        return this == ApprovalStatus.APPROVED;
    }
}