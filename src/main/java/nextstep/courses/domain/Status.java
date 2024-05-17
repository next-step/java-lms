package nextstep.courses.domain;

public enum Status {

    PREPARE("prepare"),
    END("end"),
    RECRUIT("recruit");

    private final String status;
    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static Status fromString(String status) {
        for (Status state : Status.values()) {
            if (state.getStatus().equals(status))
                return state;
        }
        throw new IllegalArgumentException(status + "는 잘못된 강의 상태 입니다");
    }
}
