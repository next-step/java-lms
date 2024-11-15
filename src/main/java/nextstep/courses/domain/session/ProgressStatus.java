package nextstep.courses.domain.session;

public enum ProgressStatus {
    PREPARE("준비중"),
    IN_PROGRESS("진행중"),
    CLOSED("종료");

    private final String description;

    ProgressStatus(String description) {
        this.description = description;
    }

    public boolean isNotInProgress() {
        return this != IN_PROGRESS;
    }

}
