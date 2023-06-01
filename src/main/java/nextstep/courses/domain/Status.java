package nextstep.courses.domain;

public enum Status {
    PREPARING, OPENING, ENDED;

    public boolean isOpening(Status status) {
        return status == OPENING;
    }
}
