package nextstep.sessions.domain;

import java.time.LocalDateTime;

import nextstep.users.domain.Instructor;

public class SessionCancelHistory {

	private Long id;

	private Instructor instructor;

	private Student student;

	private final LocalDateTime createdAt = LocalDateTime.now();

	private LocalDateTime updatedAt;

	public SessionCancelHistory(Instructor instructor, Student student) {
		this.instructor = instructor;
		this.student = student;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public Student getStudent() {
		return student;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
