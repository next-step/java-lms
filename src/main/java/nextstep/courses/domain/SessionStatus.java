package nextstep.courses.domain;

public enum SessionStatus implements EnumModel {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    END("종료");

    private final String status;

    SessionStatus(String status) {
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
