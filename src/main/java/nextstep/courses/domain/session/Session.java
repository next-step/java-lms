package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class Session {
	private Long id;

	private SessionType type;

	private SessionState sessionState;

	private int maxNumberOfStudent;

	private SessionImage sessionImage;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	public Session(SessionType type, int maxNumberOfStudent, SessionImage sessionImage, LocalDateTime startDate, LocalDateTime endDate) {
		if(type.equals(SessionType.PAID) && maxNumberOfStudent <= 0) {
			throw new IllegalArgumentException("유료 강의의 최대 수강 인원은 1 이상이여야 합니다.");
		}

		this.type = type;
		this.maxNumberOfStudent = maxNumberOfStudent;
		this.sessionImage = sessionImage;
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
