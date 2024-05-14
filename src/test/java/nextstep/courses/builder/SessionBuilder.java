package nextstep.courses.builder;

import nextstep.courses.domain.session.SessionImage;
import nextstep.courses.domain.session.SessionState;

import java.time.LocalDateTime;

abstract class SessionBuilder {
	protected Long id = 0L;

	protected SessionState sessionState = SessionState.RECRUITING;

	protected int numberOfStudent = 0;

	protected SessionImage sessionImage = new SessionImageBuilder().build();

	protected LocalDateTime startDate = LocalDateTime.of(2024, 4, 20, 12, 12, 12);

	protected LocalDateTime endDate = LocalDateTime.of(2024, 5, 20, 12, 12, 12);

	public SessionBuilder id(Long id) {
		this.id = id;
		return this;
	}

	public SessionBuilder sessionState(SessionState sessionState) {
		this.sessionState = sessionState;
		return this;
	}
}
