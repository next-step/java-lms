package nextstep.courses.domain;

import java.time.LocalDateTime;

import nextstep.courses.domain.enums.SessionStatus;

public class Session {
	private final LocalDateTime startDate;
	private final LocalDateTime endDate;
	private final SessionStatus sessionStatus;
	private int numberOfStudents;
	private CoverImageInfo coverImageInfo;

	public Session(LocalDateTime startDate, LocalDateTime endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.sessionStatus = SessionStatus.READY;
		this.numberOfStudents = 0;
	}
}
