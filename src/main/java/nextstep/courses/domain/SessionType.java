package nextstep.courses.domain;

public enum SessionType {

    FREE("무료 강의"),
    PAID("유료 강의");

    private String description;

    SessionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
