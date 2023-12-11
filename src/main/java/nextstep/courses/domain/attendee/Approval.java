package nextstep.courses.domain.attendee;

public enum Approval {

    APPROVED,
    NOT_APPROVED,
    ;

    public boolean isApproved() {
        return this.equals(APPROVED);
    }

    public boolean isNotApproved() {
        return this.equals(NOT_APPROVED);
    }
}
