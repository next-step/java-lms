package nextstep.session.domain;

public class SessionPrice {

    private static final int FREE_SESSION = 0;

    private final long sessionPrice;

    public SessionPrice(final int sessionPrice) {
        this.sessionPrice = sessionPrice;
    }

    public SessionPrice() {
        this.sessionPrice = FREE_SESSION;
    }

    public long getSessionPrice() {
        return sessionPrice;
    }

    public boolean isFree() {
        return sessionPrice == FREE_SESSION;
    }

}
