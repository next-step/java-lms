package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.util.Objects;

public class SessionMeta {
    private final SessionType sessionType;
    private final SessionPeriod period;
    private final Price price;
    private final NsImage coverImage;

    public SessionMeta(SessionType sessionType, SessionPeriod period, Price price, NsImage coverImage) {
        this.sessionType = sessionType;
        this.period = period;
        this.price = price;
        this.coverImage = coverImage;
    }

    public boolean isFree() {
        return sessionType.isFree();
    }

    public boolean isPaid() {
        return sessionType.isPaid();
    }

    public boolean notValidPayment(Payment payment) {
        return payment.notMatchWith(price);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SessionMeta that = (SessionMeta) o;
        return sessionType == that.sessionType && Objects.equals(period, that.period) && Objects.equals(price, that.price) && Objects.equals(coverImage, that.coverImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionType, period, price, coverImage);
    }
}
