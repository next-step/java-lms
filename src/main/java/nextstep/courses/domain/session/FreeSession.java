package nextstep.courses.domain.session;

import nextstep.courses.domain.course.Course;

import java.time.LocalDateTime;

public class FreeSession extends Session {
	public FreeSession(Long id, Course course, SessionState sessionState, SessionImage sessionImage, LocalDateTime startDate, LocalDateTime endDate, int numberOfStudent) {
		super(id, course, sessionState, sessionImage, startDate, endDate, numberOfStudent);
	}
}
