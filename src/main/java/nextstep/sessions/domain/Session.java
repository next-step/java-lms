package nextstep.sessions.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import nextstep.sessions.exception.GuestUserSignUpException;
import nextstep.sessions.type.ProgressType;
import nextstep.sessions.type.RecruitStatusType;
import nextstep.users.domain.NsUser;

public class Session {

	private final Long id;

	private final Long courseId;

	private final SessionDate sessionDate;

	private String coveredImageUrl;

	private boolean free;

	private final Enrollment enrollment;

	private final LocalDateTime createdAt = LocalDateTime.now();

	private LocalDateTime updatedAt;

	public Session(Long id, Long courseId, SessionDate sessionDate, String coveredImageUrl, boolean free, int capacity, Students students) {
		this(id, courseId, sessionDate, coveredImageUrl, free, ProgressType.PREPARING, capacity, students);
	}

	public Session(Long id, Long courseId, SessionDate sessionDate, String coveredImageUrl, boolean free, ProgressType progressType, int capacity, Students students) {
		this(id, courseId, sessionDate, coveredImageUrl, free, progressType, RecruitStatusType.NOT_RECRUITING, capacity, students);
	}

	public Session(Long id, Long courseId, SessionDate sessionDate, String coveredImageUrl, boolean free, ProgressType progressType, RecruitStatusType recruitStatusType, int capacity, Students students) {
		this.id = id;
		this.courseId = courseId;
		this.sessionDate = sessionDate;
		this.coveredImageUrl = coveredImageUrl;
		this.free = free;
		this.enrollment = new Enrollment(progressType, recruitStatusType, capacity, students);
	}

	public void start() {
		this.enrollment.changeProgressStatusType(ProgressType.IN_PROGRESS);
	}

	public void end() {
		this.enrollment.changeProgressStatusType(ProgressType.TERMINATION);
	}

	public void open() {
		this.enrollment.changeRecruitingStatusType(RecruitStatusType.RECRUITING);
	}

	public Student enroll(NsUser nsUser) {
		if (nsUser.isGuestUser()) {
			throw new GuestUserSignUpException("게스트 유저는 수강신청을 할 수 없습니다.");
		}
		return this.enrollment.enroll(new Student(this.id, nsUser.getId()));
	}

	public boolean contains(Student student) {
		return this.enrollment.contains(student);
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Enrollment getEnrollment() {
		return enrollment;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Session session = (Session)o;
		return free == session.free && Objects.equals(id, session.id) && Objects.equals(courseId, session.courseId) && Objects.equals(sessionDate, session.sessionDate) && Objects.equals(coveredImageUrl,
			session.coveredImageUrl) && Objects.equals(enrollment, session.enrollment);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, courseId, sessionDate, coveredImageUrl, free, enrollment);
	}

	@Override
	public String toString() {
		return "Session[" +
			"id=" + id +
			", courseId=" + courseId +
			", sessionDate=" + sessionDate +
			", coveredImageUrl='" + coveredImageUrl + '\'' +
			", free=" + free +
			", enrollment=" + enrollment +
			", createdAt=" + createdAt +
			", updatedAt=" + updatedAt +
			']';
	}
}
