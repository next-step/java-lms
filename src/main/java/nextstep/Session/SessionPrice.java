package nextstep.Session;

public class SessionPrice {

    private static final int FREE_SESSION = 0;

    private final int sessionPrice;

    public SessionPrice(final int sessionPrice) {
        this.sessionPrice = sessionPrice;
    }

    public SessionPrice() {
        this.sessionPrice = FREE_SESSION;
    }

    public int getSessionPrice() {
        return sessionPrice;
    }

    public boolean isFree() {
        return sessionPrice == FREE_SESSION;
    }

}
