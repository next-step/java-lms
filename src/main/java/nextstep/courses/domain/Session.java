package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Session {
    private static final String CAN_NOT_APPLY_STATUS = "신청할 수 없는 상태입니다.";
    private static final String NOT_MATCH_PRICE_AND_PAYMENT = "결제 금액과 수강료가 일치하지 않습니다.";

    private final Long id;
    private final Long courseId;
    private final SessionMeta meta;
    private final LectureStatus lectureStatus;
    private final RecruitmentStatus recruitmentStatus;
    private final Capacity capacity;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Session(Long courseId, SessionMeta meta, LectureStatus lectureStatus, RecruitmentStatus recruitmentStatus, Capacity capacity) {
        this(null, courseId, meta, lectureStatus, recruitmentStatus, capacity, LocalDateTime.now(), null);
    }

    public Session(Long id, Long courseId, SessionMeta meta, LectureStatus lectureStatus, RecruitmentStatus recruitmentStatus, Capacity capacity, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.courseId = courseId;
        this.meta = meta;
        this.lectureStatus = lectureStatus;
        this.recruitmentStatus = recruitmentStatus;
        this.capacity = capacity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Session startLecture() {
        return new Session(
                id,
                courseId,
                meta,
                LectureStatus.ONGOING,
                recruitmentStatus,
                capacity,
                createdAt,
                updatedAt
        );
    }

    public Session endLecture() {
        return new Session(
                id,
                courseId,
                meta,
                LectureStatus.ENDED,
                recruitmentStatus,
                capacity,
                createdAt,
                updatedAt
        );
    }

    public Session startRecruiting() {
        return new Session(
                id,
                courseId,
                meta,
                lectureStatus,
                RecruitmentStatus.RECRUITING,
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
                lectureStatus,
                RecruitmentStatus.NOT_RECRUITING,
                capacity,
                createdAt,
                updatedAt
        );
    }

    public boolean canApply() {
        return recruitmentStatus.isRecruiting() && !lectureStatus.isEnded()
                && (meta.isFree() || capacity.hasRoom());
    }

    public Application apply(Payment payment) {
        if (!canApply()) {
            throw new IllegalStateException(CAN_NOT_APPLY_STATUS);
        }

        if (meta.isPaid() && meta.notValidPayment(payment)) {
            throw new IllegalArgumentException(NOT_MATCH_PRICE_AND_PAYMENT);
        }

        return new Application(id, payment.getUserId());
    }

    public Session increaseCapacity() {
        return new Session(
                id,
                courseId,
                meta,
                lectureStatus,
                recruitmentStatus,
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

    public LectureStatus getLectureStatus() {
        return lectureStatus;
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
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

    public static Session createFree(Long courseId, SessionPeriod period, List<NsImage> images) {
        SessionMeta meta = new SessionMeta(SessionType.FREE, period, Price.free(), images);
        Capacity capacity = CapacityFactory.forFree();
        return new Session(courseId, meta, LectureStatus.PREPARING, RecruitmentStatus.NOT_RECRUITING, capacity);
    }

    public static Session createPaid(Long courseId, SessionPeriod period, List<NsImage> images, int maxParticipants, Price price) {
        SessionMeta meta = new SessionMeta(SessionType.PAID, period, price, images);
        Capacity capacity = new LimitedCapacity(maxParticipants); // currentParticipants 생략
        return new Session(courseId, meta, LectureStatus.PREPARING, RecruitmentStatus.NOT_RECRUITING, capacity);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(courseId, session.courseId) && Objects.equals(meta, session.meta) && lectureStatus == session.lectureStatus && recruitmentStatus == session.recruitmentStatus && Objects.equals(capacity, session.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, meta, lectureStatus, recruitmentStatus, capacity);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", meta=" + meta +
                ", lectureStatus=" + lectureStatus +
                ", recruitmentStatus=" + recruitmentStatus +
                ", capacity=" + capacity +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
