package nextstep.courses.domain.course.session;

public enum SessionType {
    FREE("무료"),
    CHARGE("유료");

    private final String description;

    SessionType(String description) {
        this.description = description;
    }

    public boolean charged() {
        return this == CHARGE;
    }
}
