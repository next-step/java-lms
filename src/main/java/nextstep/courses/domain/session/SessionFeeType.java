package nextstep.courses.domain.session;

public enum SessionFeeType {
    PAID, FREE;

    public static SessionFeeType toSessionFeeType(SessionFee sessionFee) {
        if (sessionFee.isFree()) {
            return FREE;
        }
        return PAID;
    }
}
