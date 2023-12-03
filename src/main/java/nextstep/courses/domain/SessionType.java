package nextstep.courses.domain;

public enum SessionType {
    FREE("FREE"), PAID("PAID");

    private final String type;

    SessionType(String type) {
        this.type = type;
    }
    public String type() {
        return type;
    }

    public boolean isFree() {
        return this == FREE;
    }

    public boolean isPaid() {
        return this == PAID;
    }

}
