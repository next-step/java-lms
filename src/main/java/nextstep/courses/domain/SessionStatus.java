package nextstep.courses.domain;

public class SessionStatus {

    private Status status;
    public SessionStatus(String status) {
        this.status = Status.fromString(status);
    }

    public boolean check() {
        return this.status == Status.RECRUIT;
    }
}
