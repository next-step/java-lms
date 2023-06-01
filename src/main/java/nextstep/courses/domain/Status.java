package nextstep.courses.domain;

public enum Status {
    PREPARING, OPENING, ENDED;

    public boolean isOpening(Status status) {
        return status == OPENING;
    }

    public static Status toStatus(String statusString) {
        try {
            return Status.valueOf(statusString);
        } catch (Exception e) {
            return null;
        }
    }
}
