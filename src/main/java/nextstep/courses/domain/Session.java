package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.util.Objects;

public class Session {
    private static final String CAN_NOT_APPLY_STATUS = "신청할 수 없는 상태입니다.";
    private static final String NOT_MATCH_PRICE_AND_PAYMENT = "결제 금액과 수강료가 일치하지 않습니다.";

    private final Long id;
    private final SessionMeta meta;
    private final SessionStatus sessionStatus;
    private final Capacity capacity;

    public Session(Long id, SessionMeta meta, SessionStatus sessionStatus, Capacity capacity) {
        this.id = id;
        this.meta = meta;
        this.sessionStatus = sessionStatus;
        this.capacity = capacity;
    }

    public Session startRecruiting() {
        return new Session(
                id,
                meta,
                SessionStatus.RECRUITING,
                capacity
        );
    }

    public Session finishRecruiting() {
        return new Session(
                id,
                meta,
                SessionStatus.CLOSED,
                capacity
        );
    }

    public boolean canApply() {
        return sessionStatus.isRecruiting()
                && (meta.isFree() || capacity.hasRoom());
    }

    public Session apply(Payment payment) {
        if (!canApply()) {
            throw new IllegalStateException(CAN_NOT_APPLY_STATUS);
        }

        if (meta.isPaid() && meta.notValidPayment(payment)) {
            throw new IllegalArgumentException(NOT_MATCH_PRICE_AND_PAYMENT);
        }

        return new Session(
                id,
                meta,
                sessionStatus,
                capacity.increase()
        );
    }

    public boolean isFree() {
        return meta.isFree();
    }

    public boolean hasRoom() {
        return capacity.hasRoom();
    }

    public static Session createFree(Long id, SessionPeriod period, NsImage image) {
        SessionMeta meta = new SessionMeta(SessionType.FREE, period, Price.free(), image);
        Capacity capacity = CapacityFactory.forFree();
        return new Session(id, meta, SessionStatus.PREPARING, capacity);
    }

    public static Session createPaid(Long id, SessionPeriod period, NsImage image, int maxParticipants, Price price) {
        SessionMeta meta = new SessionMeta(SessionType.PAID, period, price, image);
        Capacity capacity = new LimitedCapacity(maxParticipants); // currentParticipants 생략
        return new Session(id, meta, SessionStatus.PREPARING, capacity);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(meta, session.meta) && sessionStatus == session.sessionStatus && Objects.equals(capacity, session.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meta, sessionStatus, capacity);
    }
}
