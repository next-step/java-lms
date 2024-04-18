package nextstep.sessions.domain;

import nextstep.image.domain.Image;

import java.time.LocalDate;

public class Session {

	private Long id;
	private String title;
	private Long courseId;
	private LocalDate startedAt;
	private LocalDate endedAt;
	private Image image;
	private SessionType sessionType;
	private Integer maximumAttendeeNumber;
	private Integer attendeeNumber;

	public Session(final long id, final String title, final long courseId, final LocalDate startedAt, final LocalDate endedAt, final SessionType sessionType, final int maximumAttendeeNumber, final int attendeeNumber) {
		validateSessionDate(startedAt, endedAt);
		validateSessionMaximumAttendeeNumber(sessionType, maximumAttendeeNumber, attendeeNumber);
		this.id = id;
		this.title = title;
		this.courseId = courseId;
		this.startedAt = startedAt;
		this.endedAt = endedAt;
		this.sessionType = sessionType;
		this.maximumAttendeeNumber = maximumAttendeeNumber;
		this.attendeeNumber = attendeeNumber;
	}

	private void validateSessionMaximumAttendeeNumber(final SessionType sessionType, final int maximumAttendeeNumber, final int attendeeNumber) {
		if (sessionType.equals(SessionType.PAID) && maximumAttendeeNumber < attendeeNumber) {
			throw new IllegalArgumentException("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.");
		}
	}

	private static void validateSessionDate(final LocalDate startedAt, final LocalDate endedAt) {
		if (startedAt.isAfter(endedAt)) {
			throw new IllegalArgumentException("강의 종료일보다 강의 시작일이 늦을 수 없습니다.");
		}
	}
}
