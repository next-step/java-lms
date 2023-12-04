package nextstep.courses.domain;

public enum SessionStatusType {
    READY("준비중"),
    ONGOING("진행중"),
    END("종료중");

    private final String description;

    SessionStatusType(String description) {
        this.description = description;
    }

    public boolean isEndSession() {
        return this == END;
    }
}
