package nextstep.courses.domain;

public enum SessionType {

    PAID("유료강의"),

    FREE("무료강의");

    SessionType(String type) {
        this.type = type;
    }

    private final String type;

    public boolean isPaid() {
        return this == PAID;
    }
}
