package nextstep.courses.domain.session;

public enum Status {

    PREPARE(false),
    RECRUITING(true),
    COMPLETED(false);

    private final boolean isAbleRegister;

    Status(boolean isAbleRegister) {
        this.isAbleRegister = isAbleRegister;
    }

    public boolean ableRegister() {
        return isAbleRegister;
    }
}
