package nextstep.courses.domain.type;

public enum SessionProgressStatus {

    READY("준비중"),
    ONGOING("진행중"),
    TERMINATE("종료");

    private String description;

    SessionProgressStatus(String description) {
        this.description = description;
    }

    public boolean isTerminal() {
        return this == TERMINATE;
    }
}
