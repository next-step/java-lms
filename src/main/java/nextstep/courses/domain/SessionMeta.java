package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.nio.file.attribute.AttributeView;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class SessionMeta {
    private final SessionType sessionType;
    private final SessionPeriod period;
    private final Price price;
    private final List<NsImage> images;

    public SessionMeta(SessionType sessionType, SessionPeriod period, Price price, List<NsImage> images) {
        this.sessionType = sessionType;
        this.period = period;
        this.price = price;
        this.images = images;
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

    public SessionType getSessionType() {
        return sessionType;
    }

    public LocalDate getStartAt() {
        return period.getStartAt();
    }

    public LocalDate getEndAt() {
        return period.getEndAt();
    }

    public Long getPrice() {
        return price.getValue();
    }

    public List<NsImage> getImages() {
        return images;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SessionMeta that = (SessionMeta) o;
        return sessionType == that.sessionType && Objects.equals(period, that.period) && Objects.equals(price, that.price) && Objects.equals(images, that.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionType, period, price, images);
    }
}
