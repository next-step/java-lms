package nextstep.courses.domain.type;

public enum SessionStatus {

    READY("준비중"),
    RECRUITING("모집중"),
    TERMINATE("종료");

    private String description;

    SessionStatus(String description) {
        this.description = description;
    }

}
