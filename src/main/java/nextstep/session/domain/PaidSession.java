package nextstep.session.domain;

import nextstep.common.domain.BaseEntity;
import nextstep.common.domain.DeleteHistory;
import nextstep.courses.domain.Course;
import nextstep.exception.SessionException;
import nextstep.payments.domain.Payment;
import nextstep.session.dto.SessionVO;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class PaidSession implements Session {

    private final long id;
    private Duration duration;
    private Cover cover;
    private SessionStatus sessionStatus;
    private SessionName sessionName;
    private final Course course;
    private final Capacity capacity;
    private final Price price;
    private final Tutor tutor;
    private final Students students;
    private final BaseEntity baseEntity;

    public PaidSession(
            long id, Duration duration, Cover cover, String sessionName, Course course,
            int capacity, Long price, Tutor tutor
    ) {
        this.id = id;
        this.duration = duration;
        this.cover = cover;
        this.sessionStatus = SessionStatus.create();
        this.sessionName = new SessionName(sessionName);
        this.course = course;
        this.capacity = Capacity.create(capacity);
        this.price = new Price(price);
        this.tutor = tutor;
        this.students = new Students();
        this.baseEntity = new BaseEntity();
    }

    public PaidSession(
            long id, Duration duration, Cover cover, SessionStatus sessionStatus, String sessionName, Course course,
            int maxCapacity, int enrolled, Long price, Tutor tutor, Students students, BaseEntity baseEntity
    ) {
        this.id = id;
        this.duration = duration;
        this.cover = cover;
        this.sessionStatus = sessionStatus;
        this.sessionName = new SessionName(sessionName);
        this.course = course;
        this.capacity = Capacity.create(maxCapacity, enrolled);
        this.price = new Price(price);
        this.tutor = tutor;
        this.students = students;
        this.baseEntity = baseEntity;
    }

    @Override
    public void toNextSessionStatus() {
        this.sessionStatus = this.sessionStatus.toNextStatus();
    }

    @Override
    public void toPreviousSessionStatus() {
        this.sessionStatus = this.sessionStatus.toPreviousStatus();
    }

    private void validateReadyStatus() {
        if (!this.sessionStatus.onReady()) {
            throw new SessionException("강의가 준비중인 상태가 아닙니다. 변경 불가능합니다.");
        }
    }

    @Override
    public boolean isEnrollAvailable(LocalDateTime applyDate) {
        return this.sessionStatus.canEnroll() &&
                this.duration.isAvailable(applyDate) &&
                this.capacity.isAvailable();
    }

    @Override
    public boolean apply(Student student, Payment payment, LocalDateTime applyDate) {
        if (isEnrollAvailable(applyDate) && this.price.isFullyPaid(payment)) {
            this.students.add(student);
            this.capacity.enroll();
            return true;
        }

        return false;
    }

    @Override
    public SessionVO toVO() {
        return new SessionVO(
                this.id,
                this.duration.getStartDate(),
                this.duration.getEndDate(),
                this.sessionStatus.getSessionStatus().name(),
                this.course.getId(),
                this.capacity.getMaxCapacity(),
                this.capacity.getEnrolled(),
                this.price.getPrice(),
                this.tutor.getTutorId(),
                this.cover.getId(),
                this.sessionName.getSessionName(),
                this.baseEntity.isDeleted(),
                this.baseEntity.getCreatedAt(),
                this.baseEntity.getLastModifiedAt()
        );
    }

    @Override
    public DeleteHistory delete(NsUser requestUser) {
        validateCanDeleteForSessionStatus();
        this.baseEntity.delete(LocalDateTime.now());

        return DeleteHistory.createSession(this.id, requestUser, LocalDateTime.now());
    }

    private void validateCanDeleteForSessionStatus() {
        if (!this.sessionStatus.onReady()) {
            throw new SessionException("준비 상태에서만 세션을 삭제할 수 있습니다.");
        }
    }
}
