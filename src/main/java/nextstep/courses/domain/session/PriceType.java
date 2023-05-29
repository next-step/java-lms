package nextstep.courses.domain.session;

public enum PriceType {
    PAID, FREE;

    public static PriceType checkPriceType(int price) {
        if (price == 0) {
            return FREE;
        }

        return PAID;
    }
}
