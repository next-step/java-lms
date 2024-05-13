package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class FreeSession extends Session {
	public FreeSession(Long id, SessionState sessionState, int numberOfStudent, SessionImage sessionImage, LocalDateTime startDate, LocalDateTime endDate) {
		super(id, sessionState, numberOfStudent, sessionImage, startDate, endDate);
	}
}
