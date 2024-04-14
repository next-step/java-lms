package nextstep.courses.domain;

import java.time.LocalDateTime;

import nextstep.courses.domain.enums.SessionStatus;
import nextstep.payments.domain.Payment;

abstract public class Session {
	protected final SessionDate sessionDate;
	protected SessionStatus sessionStatus;
	protected int numberOfStudents;
	protected CoverImageInfo coverImageInfo;

	public Session(SessionDate sessionDate, CoverImageInfo coverImageInfo) {
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
