package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.nio.file.attribute.AttributeView;
import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private static final String CAN_NOT_APPLY_STATUS = "신청할 수 없는 상태입니다.";
    private static final String NOT_MATCH_PRICE_AND_PAYMENT = "결제 금액과 수강료가 일치하지 않습니다.";

    private final Long id;
    private final Long courseId;
    private final SessionMeta meta;
    private final SessionStatus sessionStatus;
    private final Capacity capacity;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Session(Long courseId, SessionMeta meta, SessionStatus sessionStatus, Capacity capacity) {
        this(null, courseId, meta, sessionStatus, capacity, LocalDateTime.now(), null);
    }

    public Session(Long id, Long courseId, SessionMeta meta, SessionStatus sessionStatus, Capacity capacity, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.courseId = courseId;
        this.meta = meta;
        this.sessionStatus = sessionStatus;
        this.capacity = capacity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Session startRecruiting() {
        return new Session(
                id,
                courseId,
                meta,
                SessionStatus.RECRUITING,
                capacity,
                createdAt,
                updatedAt
        );
    }

    public Session finishRecruiting() {
        return new Session(
                id,
                courseId,
                meta,
                SessionStatus.CLOSED,
                capacity,
                createdAt,
                updatedAt
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
                courseId,
                meta,
                sessionStatus,
                capacity.increase(),
                createdAt,
                updatedAt
        );
    }

    public boolean isFree() {
        return meta.isFree();
    }

    public boolean hasRoom() {
        return capacity.hasRoom();
    }

    public SessionMeta getMeta() {
        return meta;
    }

    public Long getCourseId() {
        return courseId;
    }

    public SessionStatus getStatus() {
        return sessionStatus;
    }

    public int getMax() {
        return capacity.getMax();
    }

    public Object getCurrent() {
        return capacity.getCurrent();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static Session createFree(Long courseId, SessionPeriod period, NsImage image) {
        SessionMeta meta = new SessionMeta(SessionType.FREE, period, Price.free(), image);
        Capacity capacity = CapacityFactory.forFree();
        Session session = new Session(courseId, meta, SessionStatus.PREPARING, capacity);

        return new Session(courseId, meta, SessionStatus.PREPARING, capacity);
    }

    public static Session createPaid(Long courseId, SessionPeriod period, NsImage image, int maxParticipants, Price price) {
        SessionMeta meta = new SessionMeta(SessionType.PAID, period, price, image);
        Capacity capacity = new LimitedCapacity(maxParticipants); // currentParticipants 생략
        return new Session(courseId, meta, SessionStatus.PREPARING, capacity);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(meta, session.meta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meta, sessionStatus, capacity);
    }
}
