package nextstep.courses.domain;

import nextstep.courses.enumeration.SessionProgressType;
import nextstep.courses.enumeration.SessionRecruitStatus;
import nextstep.courses.enumeration.SessionType;
import nextstep.courses.exception.CanNotRegisterSessionException;
import nextstep.courses.exception.PaymentMisMatchException;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Session extends BaseEntity {

    private final Long id;
    private final Long courseId;
    private final String title;
    private final Integer price;
    private final Integer maxParticipants;
    protected final SessionImages sessionImages;
    protected final SessionStudents sessionStudents;
    private final SessionType sessionType;
    private final SessionProgressType sessionProgressType;
    private final SessionRecruitStatus sessionRecruitStatus;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    protected Session(Long id, Long courseId, String title, SessionImages sessionImages, SessionType sessionType, SessionRecruitStatus sessionRecruitStatus, SessionProgressType sessionProgressType, int price, int maxParticipants, LocalDateTime startAt, LocalDateTime endAt) {
        super(LocalDateTime.now(), LocalDateTime.now());
        validate(startAt, endAt);
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.sessionImages = sessionImages;
        this.sessionType = sessionType;
        this.sessionStudents = SessionStudents.of(new HashSet<>());
        this.sessionRecruitStatus = sessionRecruitStatus;
        this.sessionProgressType = sessionProgressType;
        this.price = price;
        this.maxParticipants = maxParticipants;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    protected Session(Long id, Long courseId, String title, SessionType sessionType, Integer maxParticipants, Integer price, SessionRecruitStatus sessionRecruitStatus, SessionProgressType sessionProgressType, LocalDateTime startAt, LocalDateTime endAt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        validate(startAt, endAt);
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.sessionImages = SessionImages.of(new ArrayList<>());
        this.sessionType = sessionType;
        this.sessionStudents = SessionStudents.of(new HashSet<>());
        this.sessionRecruitStatus = sessionRecruitStatus;
        this.sessionProgressType = sessionProgressType;
        this.price = price;
        this.maxParticipants = maxParticipants;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private void validate(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt == null) {
            throw new IllegalArgumentException("강의 시작일이 없습니다.");
        }

        if (endAt == null) {
            throw new IllegalArgumentException("강의 종료일이 없습니다.");
        }
    }

    protected void validateSessionIsRegistering() {
        if (SessionRecruitStatus.NOT_RECRUITING.equals(this.sessionRecruitStatus) && !SessionProgressType.IN_PROGRESS.equals(this.sessionProgressType)) {
            throw new CanNotRegisterSessionException("강의가 모집 중이거나 진행 중이여야만 신청할 수 있습니다.");
        }
    }

    protected void validateRegister(Payment payment) {
        validateSessionIsRegistering();

        if (!payment.isPaidPriceSame(this.price)) {
            throw new PaymentMisMatchException("지불한 금액이 강의 가격과 일치하지 않습니다.");
        }
    }

    public final void register(SessionStudent sessionStudent, Payment payment) {
        validateRegister(payment);
        validate(this.price, this.maxParticipants);
        enroll(sessionStudent, payment);
    }

    abstract protected void enroll(SessionStudent sessionStudent, Payment payment);
    abstract protected void validate(int price, int maxParticipants);

    public long getId() {
        return this.id;
    }

    public Set<SessionStudent> getStudents() {
        return this.sessionStudents.getStudents();
    }

    public String getTitle() {
        return this.title;
    }

    public SessionType getSessionType() {
        return this.sessionType;
    }

    public SessionRecruitStatus getSessionRecuitStatus() {
        return this.sessionRecruitStatus;
    }

    public SessionProgressType getSessionProgressType() {
        return this.sessionProgressType;
    }

    public LocalDateTime getStartAt() {
        return this.startAt;
    }

    public LocalDateTime getEndAt() {
        return this.endAt;
    }

    public long getCourseId() {
        return this.courseId;
    }

    public int getMaxParticipants() {
        return this.maxParticipants;
    }

    public int getPrice() {
        return this.price;
    }

    public List<SessionImage> getSessionImages() {
        return this.sessionImages.getSessionImages();
    }
}
