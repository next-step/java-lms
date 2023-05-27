package nextstep.courses.domain.session;

public enum Status {
    PREPARING("준비중"),
    ENROLLING("모집중"),
    ENDED("종료");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}
