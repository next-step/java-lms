package nextstep.courses.domain.session;

public enum SessionStatus {

    PREPARING("준비중"),
    ENROLLMENT_OPEN("모집중"),
    CLOSED("종료");

    private String description;

    private SessionStatus(String description) {
    }
}
