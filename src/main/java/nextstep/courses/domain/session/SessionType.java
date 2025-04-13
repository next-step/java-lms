package nextstep.courses.domain.session;

public enum SessionType {
    FREE("무료"),
    PAID("유료");

    private final String description;

    SessionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPaid() {
        return this == PAID;
    }
}