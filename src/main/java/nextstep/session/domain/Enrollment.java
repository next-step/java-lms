package nextstep.session.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class Enrollment {
	private static final int INCREASE_STUDENT = 1;
	private long id;
	private final int maximumNumberOfParticipants;
	private final long sessionPrice;
	private long sessionId;

	private SessionStatus sessionStatus;
	private NsUsers nsUsers;

	public Enrollment(int maximumNumberOfParticipants, long sessionPrice) {
		this(0L, maximumNumberOfParticipants, sessionPrice, 0L, SessionStatus.PREPARING, new NsUsers());
	}

	public Enrollment(long id, int maximumNumberOfParticipants, long sessionPrice, long sessionId) {
		this(id, maximumNumberOfParticipants, sessionPrice, sessionId, SessionStatus.PREPARING, new NsUsers());
	}

	public Enrollment(long id, int maximumNumberOfParticipants, long sessionPrice, long sessionId, SessionStatus sessionStatus, NsUsers nsUsers) {
		this.id = id;
		this.maximumNumberOfParticipants = maximumNumberOfParticipants;
		this.sessionPrice = sessionPrice;
		this.sessionId = sessionId;
		this.sessionStatus = sessionStatus;
		this.nsUsers = nsUsers;
	}

	private boolean isSessionRegister() {
		if (sessionStatus != SessionStatus.PREPARING) {
			throw new IllegalArgumentException("강의가 준비중이 아닙니다.");
		}
		return true;
	}

	private boolean validateParticipantsLimit() {
		if (maximumNumberOfParticipants < nsUsers.getNumberOfStudent() + INCREASE_STUDENT) {
			throw new IllegalArgumentException("수강인원이 초과되었습니다.");
		}
		return true;
	}

	public boolean isMaximumNumberOfParticipantsLimited(int numberOfParticipants) {
		return numberOfParticipants <= maximumNumberOfParticipants;
	}

	public boolean isSamePaymentAndSessionPrice(int payment) {
		return payment == sessionPrice;
	}


	private boolean isAddStudent() {
		return isSessionRegister() && validateParticipantsLimit();
	}

	public void applySession(NsUser student) {
		if (isAddStudent()) {
			nsUsers.addStudent(student);
		}
	}

	public long getId() {
		return id;
	}

	public int getMaximumNumberOfParticipants() {
		return maximumNumberOfParticipants;
	}

	public int getSessionStatus() {
		return sessionStatus.getStatusValue();
	}

	public long getSessionPrice() {
		return sessionPrice;
	}

	public long getSessionId() {
		return sessionId;
	}

	@Override
	public String toString() {
		return "Enrollment{" +
				"id=" + id +
				", maximumNumberOfParticipants=" + maximumNumberOfParticipants +
				", sessionPrice=" + sessionPrice +
				", sessionStatus=" + sessionStatus +
				", nsUsers=" + nsUsers +
				'}';
	}

}
