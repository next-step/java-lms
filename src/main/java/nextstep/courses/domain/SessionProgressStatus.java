package nextstep.courses.domain;

public enum SessionProgressStatus implements EnumModel {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    PROGRESSING("진행중"),
    END("종료");

    private final String status;

    SessionProgressStatus(String status) {
        this.status = status;
    }

    public boolean canEnroll() {
        return this == RECRUITING || this == PROGRESSING;
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
