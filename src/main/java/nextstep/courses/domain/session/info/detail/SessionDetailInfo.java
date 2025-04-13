package nextstep.courses.domain.session.info.detail;

import nextstep.courses.domain.session.SessionType;
import nextstep.payments.domain.Payment;
import java.time.LocalDateTime;

public class SessionDetailInfo {
    private final SessionPeriod period;
    private final SessionPrice price;

    public SessionDetailInfo(LocalDateTime startDate, LocalDateTime endDate, SessionType type, int price) {
        this.period = new SessionPeriod(startDate, endDate);
        this.price = new SessionPrice(type, price);
    }

    public boolean isPaid() {
        return price.isPaid();
    }

    public SessionType getType() {
        return price.getType();
    }

    public int getPriceValue() {
        return price.getPrice();
    }

    public void validatePayment(Payment payment) {
        price.validatePayment(payment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionDetailInfo that = (SessionDetailInfo) o;
        return period.equals(that.period) && price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(period, price);
    }
} 