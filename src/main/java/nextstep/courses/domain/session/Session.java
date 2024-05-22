package nextstep.courses.domain.session;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.student.Students;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public abstract class Session implements Enrollment {
	private Long id;

	private Course course;

	private SessionState state;

	private SessionImage image;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private int numberOfStudent;

	private Students students;


	public Session(Long id, Course course, SessionState state, SessionImage image, LocalDateTime startDate, LocalDateTime endDate, int numberOfStudent) {
		this(id, course, state, image, startDate, endDate, numberOfStudent, new Students());
	}

	public Session(Long id, Course course, SessionState state, SessionImage image, LocalDateTime startDate, LocalDateTime endDate, int numberOfStudent, Students students) {
		this.id = id;
		this.course = course;
		this.state = state;
		this.image = image;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numberOfStudent = numberOfStudent;
		this.students = students;
	}

	public Long getId() {
		return id;
	}

	public int getNumberOfStudent() {
		return numberOfStudent;
	}

	@Override
	public void enroll(NsUser nsUser, Payment payment) {
		if(!state.equals(SessionState.RECRUITING)) {
			throw new IllegalStateException("모집 중인 강의만 수강 신청 가능합니다.");
		}

		this.numberOfStudent++;

		students.add(nsUser, this);
	}

	public void end() {
		this.state = SessionState.END;
	}
}
