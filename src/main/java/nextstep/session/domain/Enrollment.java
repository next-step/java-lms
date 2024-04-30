package nextstep.session.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class Enrollment {
	private static final int INCREASE_STUDENT = 1;
	private long id;
	private final int maximumNumberOfParticipants;
	private SessionStatus sessionStatus;
	private NsUsers nsUsers;

	public Enrollment(int maximumNumberOfParticipants) {
		this(0L, SessionStatus.PREPARING, maximumNumberOfParticipants);
	}

	public Enrollment(long id, SessionStatus sessionStatus, int maximumNumberOfParticipants) {
		this.id = id;
		this.sessionStatus = sessionStatus;
		this.maximumNumberOfParticipants = maximumNumberOfParticipants;
		this.nsUsers = new NsUsers();
	}

	private boolean isSessionRegister() {
		if (sessionStatus != SessionStatus.PREPARING) {
			throw new IllegalArgumentException("강의가 준비중이 아닙니다.");
		}
		return true;
	}

	private boolean isParticipantsSession() {
		if (maximumNumberOfParticipants < nsUsers.getNumberOfStudent() + INCREASE_STUDENT) {
			throw new IllegalArgumentException("수강인원이 초과되었습니다.");
		}
		return true;
	}

	public boolean isMaximumNumberOfParticipantsLimited(int numberOfParticipants) {
		return numberOfParticipants <= maximumNumberOfParticipants;
	}

	private boolean isAddStudent() {
		return isSessionRegister() && isParticipantsSession();
	}

	public void applySession(NsUser student) {
		if (isAddStudent()) {
			nsUsers.addStudent(student);
		}
	}

}
