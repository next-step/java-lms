package nextstep.sessions.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import nextstep.sessions.exception.AlreadySignUpException;
import nextstep.sessions.exception.CapacityNumberException;
import nextstep.sessions.exception.GuestUserSignUpException;
import nextstep.sessions.exception.NotRecruitingException;
import nextstep.sessions.exception.NumberFullException;
import nextstep.sessions.type.StatusType;
import nextstep.users.domain.NsUser;

public class Session {

	private final Long id;

	private final Long courseId;

	private final SessionDate sessionDate;

	private String coveredImageUrl;

	private boolean free;

	private StatusType statusType;

	private final int capacity;

	private Students students;

	private final LocalDateTime createdAt = LocalDateTime.now();

	private LocalDateTime updatedAt;

	public Session(Long id, Long courseId, SessionDate sessionDate, String coveredImageUrl, boolean free, int capacity, Students students) {
		this(id, courseId, sessionDate, coveredImageUrl, free, StatusType.PREPARING, capacity, students);
	}

	public Session(Long id, Long courseId, SessionDate sessionDate, String coveredImageUrl, boolean free, StatusType statusType, int capacity, Students students) {
		if (capacity < 0) {
			throw new CapacityNumberException("최대 수강 인원은 음수일 수 없습니다.");
		}
		this.id = id;
		this.courseId = courseId;
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
			throw new GuestUserSignUpException("게스트 유저는 수강신청을 할 수 없습니다.");
		}

		Student student = new Student(this.id, nsUser.getId());
		if (this.students.contains(student)) {
			throw new AlreadySignUpException("이미 수강신청한 유저입니다.");
		}

		this.students.add(student);

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

	public Student lastStudent() {
		return students.last();
	}

	public long getId() {
		return id;
	}

	public Long getCourseId() {
		return courseId;
	}

	public SessionDate getSessionDate() {
		return sessionDate;
	}

	public String getCoveredImageUrl() {
		return coveredImageUrl;
	}

	public boolean isFree() {
		return free;
	}

	public StatusType getStatusType() {
		return statusType;
	}

	public int getCapacity() {
		return capacity;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setStudents(Students students) {
		this.students = students;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Session session = (Session)o;
		return free == session.free && Objects.equals(id, session.id) && Objects.equals(courseId, session.courseId) && Objects.equals(sessionDate, session.sessionDate) && Objects.equals(
			coveredImageUrl, session.coveredImageUrl) && statusType == session.statusType && Objects.equals(students, session.students);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, courseId, sessionDate, coveredImageUrl, free, statusType, students);
	}

	@Override
	public String toString() {
		return "Session [" +
			"id=" + id +
			", courseId=" + courseId +
			", sessionDate=" + sessionDate +
			", coveredImageUrl='" + coveredImageUrl + '\'' +
			", free=" + free +
			", statusType=" + statusType +
			", capacity=" + capacity +
			", students=" + students +
			", createdAt=" + createdAt +
			", updatedAt=" + updatedAt +
			']';
	}
}
