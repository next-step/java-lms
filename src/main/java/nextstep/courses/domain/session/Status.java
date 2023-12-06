package nextstep.courses.domain.session;

public enum Status {

    PREPARE("준비중"),
    RECRUIT("모집중"),
    FINISH("종료");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public static boolean isRecruiting(Status status) {
        return RECRUIT.equals(status);
    }

    public String description() {
        return this.description;
    }
}
