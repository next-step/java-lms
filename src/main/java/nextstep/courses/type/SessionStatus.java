package nextstep.courses.type;

public enum SessionStatus {
    READY, RECRUIT, FINISH;

    public boolean isFinish() {
        return this.equals(FINISH);
    }
}
