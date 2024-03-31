package nextstep.courses.domain;

public enum SessionStatus {

    PREPARE("준비중"),
    RECRUIT("모집중"),
    END("종료");

    private final String name;

    SessionStatus(String name) {
        this.name = name;
    }
}
