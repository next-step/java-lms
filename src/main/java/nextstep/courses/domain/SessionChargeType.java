package nextstep.courses.domain;

public enum SessionChargeType {
    FREE, CHARGED;

    private static final int FREE_OF_CHARGE = 0;

    public static SessionChargeType of(int price) {
        if(price == FREE_OF_CHARGE) {
            return FREE;
        }

        return CHARGED;
    }
}
