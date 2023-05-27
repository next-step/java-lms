package nextstep.courses.domain;

import java.util.Map;

public enum ApprovalStatus {
    REQUEST("신청"),
    APPROVAL("승인"),
    REJECTION("거절");

    private final String approvalStatus;

    private static final Map<String, ApprovalStatus> approvalStatusMap = Map.of(REQUEST.getApprovalStatus(), REQUEST, APPROVAL.getApprovalStatus(), APPROVAL, REJECTION.getApprovalStatus(), REJECTION);

    public static ApprovalStatus find(String approvalStatus) {
        return approvalStatusMap.get(approvalStatus);
    }

    ApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }
}
