package nextstep.courses.domain;

import java.util.Map;

public enum ApprovalStatus {
    REQUEST("신청"),
    APPROVAL("승인"),
    REJECTION("거절"),
    NO_INFO("없음");

    private final String approvalStatus;

    private static final Map<String, ApprovalStatus> approvalStatusMap = Map.of(REQUEST.name(), REQUEST, APPROVAL.name(), APPROVAL, REJECTION.name(), REJECTION);

    public static ApprovalStatus find(String approvalStatus) {
        if (approvalStatus == null) {
            return NO_INFO;
        }
        return approvalStatusMap.get(approvalStatus);
    }

    ApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

}
