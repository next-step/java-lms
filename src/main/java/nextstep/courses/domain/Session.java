package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionStatus;
import nextstep.payments.domain.Payment;

abstract public class Session {
    protected final SessionDate sessionDate;
    protected SessionStatus sessionStatus;
    protected int numberOfStudents;
    protected CoverImageInfo coverImageInfo;

    protected Session(SessionDate sessionDate, CoverImageInfo coverImageInfo) {
        this.sessionDate = sessionDate;
        this.sessionStatus = SessionStatus.READY;
        this.numberOfStudents = 0;
    }

    abstract public void enroll(Payment payment);

    public void startSession() {
        sessionStatus = SessionStatus.RECRUITING;
    }

    public boolean hasNumberOfStudents(int targetCount) {
        return numberOfStudents == targetCount;
    }
}
