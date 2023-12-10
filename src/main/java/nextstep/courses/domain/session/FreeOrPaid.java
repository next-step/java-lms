package nextstep.courses.domain.session;

public enum FreeOrPaid {
    FREE, PAID;

    public static boolean isFree(FreeOrPaid freeOrPaid) {
        return freeOrPaid == FREE;
    }
}
