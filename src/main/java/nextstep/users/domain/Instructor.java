package nextstep.users.domain;

import java.time.LocalDateTime;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.Sessions;
import nextstep.sessions.domain.Student;
import nextstep.users.exception.NotOwnedSessionException;
import nextstep.users.exception.NotStudentOfSessionException;

public class Instructor {

	private Long id;

	private final Sessions sessions;

	private final LocalDateTime createdAt = LocalDateTime.now();

	private LocalDateTime updatedAt;

	public Instructor(Long id, Sessions sessions) {
		this.id = id;
		this.sessions = sessions;
	}

	public Student enrollCancel(Session session, Student student) {
		if (!this.sessions.contains(session)) {
			throw new NotOwnedSessionException("해당 강사의 수업이 아닙니다.");
		}

		if (!session.contains(student)) {
			throw new NotStudentOfSessionException("해당 수업에 수강신청한 수강생이 아닙니다.");
		}

		return student.enrollCancel();
	}

	public Long getId() {
		return id;
	}

	public Sessions getSessions() {
		return sessions;
	}
}
