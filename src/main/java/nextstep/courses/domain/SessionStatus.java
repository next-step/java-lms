package nextstep.courses.domain;

public enum SessionStatus {
    READY(false), RECRUIT(true), END(false);

    SessionStatus(boolean canRecruit) {
        this.canRecruit = canRecruit;
    }

    private final boolean canRecruit;

    public boolean canRecruit() {
        return canRecruit;
    }
}
