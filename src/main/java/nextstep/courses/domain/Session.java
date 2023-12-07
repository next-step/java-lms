package nextstep.courses.domain;

import nextstep.courses.enumeration.SessionStatus;
import nextstep.courses.enumeration.SessionType;
import nextstep.courses.exception.CanNotRegisterSessionException;
import nextstep.courses.exception.ExceedStudentsCountException;
import nextstep.courses.exception.PaymentMisMatchException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class Session extends BaseEntity {

    private final Long id;
    private final Long courseId;
    private final String title;
    private final Integer price;
    private final Integer maxParticipants;
    protected final SessionImages sessionImages;
    protected final Students students;
    private final SessionType sessionType;
    private final SessionStatus status;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    protected Session(Long id, Long courseId, String title, SessionImages sessionImages, SessionType sessionType, SessionStatus status, int price, int maxParticipants, LocalDateTime startAt, LocalDateTime endAt) {
        super(LocalDateTime.now(), LocalDateTime.now());
        validate(startAt, endAt);
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.sessionImages = sessionImages;
        this.sessionType = sessionType;
        this.students = new Students(new HashSet<>());
        this.status = status;
        this.price = price;
        this.maxParticipants = maxParticipants;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    protected Session(Long id, Long courseId, String title, SessionType sessionType, Integer maxParticipants, Integer price, SessionStatus status, LocalDateTime startAt, LocalDateTime endAt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        validate(startAt, endAt);
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.sessionImages = new SessionImages(new ArrayList<>());
        this.sessionType = sessionType;
        this.students = new Students(new HashSet<>());
        this.status = status;
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
        if (!SessionStatus.REGISTERING.equals(this.status)) {
            throw new CanNotRegisterSessionException("강의가 모집중이여야만 신청할 수 있습니다.");
        }
    }

    protected void validateRegister(Payment payment) {
        validateSessionIsRegistering();

        if (this.sessionType.equals(SessionType.COST_MONEY) && this.students.isMaxParticipants(this.maxParticipants)) {
            throw new ExceedStudentsCountException("정원이 초과되어 더 이상 신청할 수 없습니다.");
        }

        if (!payment.isPaidPriceSame(this.price)) {
            throw new PaymentMisMatchException("지불한 금액이 강의 가격과 일치하지 않습니다.");
        }
    }

    abstract public void register(NsUser nsUser, Payment payment);
    abstract protected void validate(int price, int maxParticipants);


    public long getId() {
        return this.id;
    }

    public Set<NsUser> getStudents() {
        return this.students.getStudents();
    }

    public String getTitle() {
        return this.title;
    }

    public SessionType getSessionType() {
        return this.sessionType;
    }

    public SessionStatus getStatus() {
        return this.status;
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
}
