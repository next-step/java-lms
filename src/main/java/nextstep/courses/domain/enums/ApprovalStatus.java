package nextstep.courses.domain.enums;

public enum ApprovalStatus {
    APPROVED("승인"),
    CANCELED("취소"),
    PENDING("대기중");

    private String description;

    ApprovalStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}