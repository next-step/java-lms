package nextstep.session.domain;

public enum RecruitmentStatus {
    RECRUITING("recruiting"),
    NOT_RECRUITING("not_recruiting");

    private String status;

    RecruitmentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
