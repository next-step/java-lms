package nextstep.session.domain;

import nextstep.courses.domain.Course;
import nextstep.exception.SessionException;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class PaidSession implements Session {

    private Duration duration;
    private Cover cover;
    private SessionStatus sessionStatus;
    private SessionName sessionName;
    private final Course course;
    private final Capacity capacity;
    private final Price price;
    private final Long sessionId;
    private final Tutor tutor;
    private final Students students;

    public PaidSession(
            Duration duration, Cover cover, String sessionName, Course course,
            int capacity, Long price, Long sessionId, Tutor tutor
    ) {
        this.duration = duration;
        this.cover = cover;
        this.sessionStatus = SessionStatus.create();
        this.sessionName = new SessionName(sessionName);
        this.course = course;
        this.capacity = Capacity.create(capacity);
        this.price = new Price(price);
        this.sessionId = sessionId;
        this.tutor = tutor;
        this.students = new Students();
    }

    @Override
    public void changeStartDate(LocalDateTime startDate) {
        validateReadyStatus();

        this.duration = duration.changeStartDate(startDate);
    }

    private void validateReadyStatus() {
        if (!this.sessionStatus.onReady()) {
            throw new SessionException("강의가 준비중인 상태가 아닙니다. 변경 불가능합니다.");
        }
    }

    @Override
    public void changeEndDate(LocalDateTime endDate) {
        validateReadyStatus();

        this.duration = duration.changeEndDate(endDate);
    }

    @Override
    public void changeCover(Cover cover) {
        validateReadyStatus();

        this.cover = cover;
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
    public void editSessionName(String sessionName) {
        validateReadyStatus();

        this.sessionName = this.sessionName.editSessionName(sessionName);
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
}
