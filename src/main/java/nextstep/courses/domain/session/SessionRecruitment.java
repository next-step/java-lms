package nextstep.courses.domain.session;

public enum SessionRecruitment {
    OPEN("모집중"),
    CLOSE("비모집중");

    public final String description;

    SessionRecruitment(String description) {
        this.description = description;
    }

    public boolean isClosed() {
        return this.equals(CLOSE);
    }
}
