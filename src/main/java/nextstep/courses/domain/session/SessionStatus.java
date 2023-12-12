package nextstep.courses.domain.session;

public enum SessionStatus {

    PREPARE("준비중"),
    PROGRESS("진행중"),
    FINISH("종료");

    private String description;

    SessionStatus(String description) {
        this.description = description;
    }

    public static boolean isNotProgressing(SessionStatus sessionStatus) {
        return !PROGRESS.equals(sessionStatus);
    }

    public String description() {
        return this.description;
    }
}
