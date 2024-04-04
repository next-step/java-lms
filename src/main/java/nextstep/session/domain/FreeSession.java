package nextstep.session.domain;

import nextstep.payments.domain.Payment;
import nextstep.session.type.SessionStatusType;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class FreeSession implements Session {

    public static final int FREE_PRICE = 0;
    private Duration duration;
    private Cover cover;
    private SessionStatus sessionStatus;
    private SessionName sessionName;
    private final Capacity capacity;
    private final Price price;
    private final Long sessionId;
    private final Tutor tutor;
    private final Students students;

    public FreeSession(
            Duration duration, Cover cover, String sessionName,
            Long sessionId, Tutor tutor
    ) {
        this.duration = duration;
        this.cover = cover;
        this.sessionStatus = SessionStatus.create();
        this.sessionName = new SessionName(sessionName);
        this.capacity = Capacity.create();
        this.price = new Price(FREE_PRICE);
        this.sessionId = sessionId;
        this.tutor = tutor;
        this.students = new Students();
    }

    @Override
    public void changeStartDate(LocalDateTime startDate) {
        this.duration = duration.changeStartDate(startDate);
    }

    @Override
    public void changeEndDate(LocalDateTime endDate) {
        this.duration = duration.changeEndDate(endDate);
    }

    @Override
    public void changeCover(Cover cover) {
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
        this.sessionName = this.sessionName.editSessionName(sessionName);
    }

    @Override
    public boolean isEnrollAvailable(LocalDateTime applyDate) {
        return this.sessionStatus.getSessionStatus() == SessionStatusType.ON_ENROLL &&
                this.duration.isAvailable(applyDate);
    }

    @Override
    public boolean apply(NsUser student, Payment payment, LocalDateTime applyDate) {
        if (isEnrollAvailable(applyDate)) {
            this.students.add(student);
            this.capacity.enroll();
            return true;
        }

        return false;
    }
}
