package nextstep.courses.domain;

public enum SessionRecruitmentStatus implements EnumModel {
    RECRUITING("모집중"),
    NOT_RECRUITING("비모집중");

    private final String status;

    SessionRecruitmentStatus(String status) {
        this.status = status;
    }

    public boolean canEnroll() {
        return this == RECRUITING;
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
