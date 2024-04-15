package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import nextstep.payments.domain.Payment;

abstract public class Session {
	protected final Long id;
	protected final SessionDate sessionDate;
	protected SessionStatus sessionStatus = SessionStatus.READY;;
	protected int numberOfStudents;
	protected CoverImageInfo coverImageInfo;
	protected final SessionType type;

	protected Session(Long id, SessionDate sessionDate, CoverImageInfo coverImageInfo, SessionType type) {
		this.id = id;
		this.sessionDate = sessionDate;
		this.numberOfStudents = 0;
		this.type = type;
	}

	protected Session(Long id, SessionDate sessionDate, SessionStatus sessionStatus, int numberOfStudents, CoverImageInfo coverImageInfo, SessionType type) {
		this.id = id;
		this.sessionDate = sessionDate;
		this.sessionStatus = sessionStatus;
		this.numberOfStudents = numberOfStudents;
		this.coverImageInfo = coverImageInfo;
		this.type = type;
	}

	abstract public void enroll(Payment payment);

	public void startRecruit() {
		sessionStatus = SessionStatus.RECRUITING;
	}

	public boolean hasNumberOfStudents(int targetCount) {
		return numberOfStudents == targetCount;
	}

	public Long getId() {return id;}
	public SessionDate getSessionDate() {
		return sessionDate;
	}

	public SessionStatus getSessionStatus() {
		return sessionStatus;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public CoverImageInfo getCoverImageInfo() {
		return coverImageInfo;
	}

	public SessionType getType() {
		return type;
	}
}
