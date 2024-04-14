package nextstep.courses.domain;

import java.time.LocalDateTime;

import nextstep.courses.domain.enums.SessionStatus;
import nextstep.payments.domain.Payment;

abstract public class Session {
	private final LocalDateTime startDate;
	private final LocalDateTime endDate;
	protected SessionStatus sessionStatus;
	protected int numberOfStudents;
	private CoverImageInfo coverImageInfo;

	public Session(LocalDateTime startDate, LocalDateTime endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
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
