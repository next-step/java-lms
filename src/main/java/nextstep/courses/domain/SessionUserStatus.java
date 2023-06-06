package nextstep.courses.domain;

public enum SessionUserStatus implements EnumModel {
    WAIT("대기"),
    APPROVAL("승인"),
    REJECT("거절");

    private String status;

    SessionUserStatus(String status) {
        this.status = status;
    }

    public boolean isApproved() {
        return this == APPROVAL;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return status;
    }
}
