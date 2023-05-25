package nextstep.courses.domain;

public enum SessionChargeType {
    FREE, CHARGED;

    public static SessionChargeType of(SessionPrice price) {
        if(price.isFree()) {
            return FREE;
        }

        return CHARGED;
    }
}
