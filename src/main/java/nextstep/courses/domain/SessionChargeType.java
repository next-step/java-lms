package nextstep.courses.domain;

public enum SessionChargeType {
    FREE, CHARGED;

    private static final long FREE_OF_CHARGE = 0L;

    public static SessionChargeType of(long price) {
        if(price == FREE_OF_CHARGE) {
            return FREE;
        }

        return CHARGED;
    }
}
