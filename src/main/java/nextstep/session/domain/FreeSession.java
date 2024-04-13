package nextstep.session.domain;

import nextstep.common.domain.BaseEntity;
import nextstep.common.domain.DeleteHistory;
import nextstep.common.domain.DeleteHistoryTargets;
import nextstep.exception.SessionException;
import nextstep.payments.domain.Payment;
import nextstep.session.dto.SessionVO;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class FreeSession implements Session {

    public static final int FREE_PRICE = 0;
    private final long id;
    private Duration duration;
    private Cover cover;
    private SessionStatus sessionStatus;
    private SessionName sessionName;
    private final long courseId;
    private final Capacity capacity;
    private final Price price;
    private final Tutor tutor;
    private final Students students;
    private final BaseEntity baseEntity;

    public FreeSession(
            long id, Duration duration, Cover cover, String sessionName,
            long courseId, Tutor tutor
    ) {
        this.id = id;
        this.duration = duration;
        this.cover = cover;
        this.sessionStatus = SessionStatus.create();
        this.sessionName = new SessionName(sessionName);
        this.courseId = courseId;
        this.capacity = Capacity.create(Integer.MAX_VALUE);
        this.price = new Price(FREE_PRICE);
        this.tutor = tutor;
        this.students = new Students();
        this.baseEntity = new BaseEntity();
    }

    public FreeSession(
            long id, Duration duration, Cover cover, SessionStatus sessionStatus, String sessionName,
            long courseId, Tutor tutor, Students students, BaseEntity baseEntity
    ) {
        this.id = id;
        this.duration = duration;
        this.cover = cover;
        this.sessionStatus = sessionStatus;
        this.sessionName = new SessionName(sessionName);
        this.courseId = courseId;
        this.capacity = Capacity.create(Integer.MAX_VALUE);
        this.price = new Price(FREE_PRICE);
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

    @Override
    public void changeEnroll() {
        this.sessionStatus = this.sessionStatus.changeEnroll();
    }

    @Override
    public void changeNotEnroll() {
        this.sessionStatus = this.sessionStatus.changeNotEnroll();
    }

    @Override
    public boolean isEnrollAvailable(LocalDateTime applyDate) {
        return this.sessionStatus.canEnroll() &&
                this.duration.isAvailable(applyDate);
    }

    @Override
    public boolean apply(Student student, Payment payment, LocalDateTime applyDate) {
        if (isEnrollAvailable(applyDate)) {
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
                this.courseId,
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
    public Cover getCover() {
        return this.cover;
    }

    @Override
    public Students getStudents() {
        return this.students;
    }

    @Override
    public DeleteHistoryTargets delete(NsUser requestUser) {
        validateCanDeleteForSessionStatus();
        DeleteHistoryTargets deleteHistoryTargets = new DeleteHistoryTargets();

        deleteHistoryTargets.addFirst(this.cover.delete(requestUser));
        deleteHistoryTargets.add(this.students.deleteAll(requestUser));

        this.baseEntity.delete(LocalDateTime.now());
        deleteHistoryTargets.addFirst(DeleteHistory.createSession(this.id, requestUser, LocalDateTime.now()));
        return deleteHistoryTargets;
    }

    private void validateCanDeleteForSessionStatus() {
        if (!this.sessionStatus.onReady()) {
            throw new SessionException("준비 상태에서만 세션을 삭제할 수 있습니다.");
        }
    }
}
