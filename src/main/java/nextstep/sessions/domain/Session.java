package nextstep.sessions.domain;

import java.util.Objects;

import nextstep.sessions.exception.AlreadySignUpException;
import nextstep.sessions.exception.CapacityNumberException;
import nextstep.sessions.exception.NumberFullException;
import nextstep.sessions.exception.GuestUserSignUpException;
import nextstep.sessions.exception.NotRecruitingException;
import nextstep.sessions.type.StatusType;
import nextstep.users.domain.NsUser;

public class Session {

	private Long sessionId;

	private Long courseId;

	private SessionDate sessionDate;

	private String coveredImageUrl;

	private boolean free;

	private StatusType statusType;

	private final int capacity;

	private final Students students;

	public Session(SessionDate sessionDate, String coveredImageUrl, boolean free, int capacity, Students students) {
		this(sessionDate, coveredImageUrl, free, StatusType.PREPARING, capacity, students);
	}

	public Session(SessionDate sessionDate, String coveredImageUrl, boolean free, StatusType statusType, int capacity, Students students) {
		if (capacity < 0) {
			throw new CapacityNumberException("최대 수강 인원은 음수일 수 없습니다.");
		}
		this.sessionDate = sessionDate;
		this.coveredImageUrl = coveredImageUrl;
		this.free = free;
		this.statusType = statusType;
		this.capacity = capacity;
		this.students = students;
	}

	public Session signUp(NsUser nsUser) {
		if (!this.statusType.isRecruiting()) {
			throw new NotRecruitingException("모집중인 강의가 아닙니다.");
		}
		if (this.students.isFull(this.capacity)) {
			throw new NumberFullException("정원이 가득찼습니다.");
		}
		if (nsUser.isGuestUser()) {
			throw new GuestUserSignUpException("게스트 유저는 수강신청 할 수 없습니다.");
		}
		if (this.students.contains(nsUser)) {
			throw new AlreadySignUpException("이미 수강신청이 완료된 계정입니다.");
		}

		this.students.add(nsUser);

		return this;
	}

	public void open() {
		this.changeStatusType(StatusType.RECRUITING);
	}

	public void close() {
		this.changeStatusType(StatusType.TERMINATION);
	}

	private void changeStatusType(StatusType statusType) {
		this.statusType = StatusType.of(statusType);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Session session = (Session)o;
		return free == session.free && Objects.equals(sessionId, session.sessionId) && Objects.equals(courseId, session.courseId) && Objects.equals(sessionDate, session.sessionDate) && Objects.equals(
			coveredImageUrl, session.coveredImageUrl) && statusType == session.statusType && Objects.equals(students, session.students);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sessionId, courseId, sessionDate, coveredImageUrl, free, statusType, students);
	}
}
