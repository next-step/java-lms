package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.util.Objects;

public class Session {
    private static final String CAN_NOT_APPLY_STATUS = "신청할 수 없는 상태입니다.";
    private static final String NOT_MATCH_PRICE_AND_PAYMENT = "결제 금액과 수강료가 일치하지 않습니다.";

    private final Long id;
    private final SessionMeta meta;
    private final SessionPeriod period;
    private final Capacity capacity;
    private final Price price;

    public Session(Long id, SessionMeta meta, SessionPeriod period, Capacity capacity, Price price) {
        this.id = id;
        this.meta = meta;
        this.period = period;
        this.capacity = capacity;
        this.price = price;
    }

    public Session startRecruiting() {
        return new Session(
                id,
                meta.startRecruiting(),
                period,
                capacity,
                price
        );
    }

    public Session finishRecruiting() {
        return new Session(
                id,
                meta.finishRecruiting(),
                period,
                capacity,
                price
        );
    }

    public boolean canApply() {
        return meta.isRecruiting()
                && (meta.isFree() || capacity.hasRoom());
    }

    public Session apply(Payment payment) {
        if (!canApply()) {
            throw new IllegalStateException(CAN_NOT_APPLY_STATUS);
        }

        if (meta.isPaid() && payment.notMatchWith(price)) {
            throw new IllegalArgumentException(NOT_MATCH_PRICE_AND_PAYMENT);
        }

        return new Session(
                id,
                meta,
                period,
                capacity.increase(),
                price
        );
    }

    public boolean isFree() {
        return meta.isFree();
    }

    public boolean hasRoom() {
        return capacity.hasRoom();
    }

    public static Session createFree(Long id, SessionPeriod period, NsImage image) {
        SessionMeta meta = new SessionMeta(SessionType.FREE, SessionStatus.PREPARING, image);
        Capacity capacity = CapacityFactory.forFree();
        return new Session(id, meta, period, capacity, Price.free());
    }

    public static Session createPaid(Long id, SessionPeriod period, NsImage image, int maxParticipants, Price price) {
        SessionMeta meta = new SessionMeta(SessionType.PAID, SessionStatus.PREPARING, image);
        Capacity capacity = new LimitedCapacity(maxParticipants); // currentParticipants 생략
        return new Session(id, meta, period, capacity, price);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(meta, session.meta) && Objects.equals(period, session.period) && Objects.equals(capacity, session.capacity) && Objects.equals(price, session.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meta, period, capacity, price);
    }
}
