package nextstep.Session;

public class SessionPricing {

    private SessionPrice price;
    private int maxParticipants;

    public SessionPricing(SessionPrice price, int maxParticipants) {
        this.price = price;
        this.maxParticipants = maxParticipants;
    }

    public SessionPricing(SessionPrice price) {
        this.price = price;
    }

    public void canEnroll(final int payment, final int currentParticipants) throws IllegalArgumentException {
        if (currentParticipants >= maxParticipants) {
            throw new IllegalArgumentException("최대 수강 인원을 초과했습니다.");
        }
        if (payment != price.getSessionPrice()) {
            throw new IllegalArgumentException("결제 금액이 수강료와 일치하지 않습니다.");
        }
    }

    public boolean isFreeSession() {
        return price.isFree();
    }

}
