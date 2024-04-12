package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {

	private Long id;
	private String title;
	private Long courseId;
	private LocalDate startedAt;
	private LocalDate endedAt;
	private Image image;

	public Session(final long id, final String title, final long courseId, final LocalDate startedAt, final LocalDate endedAt) {
		validateSessionDate(startedAt, endedAt);
		this.id = id;
		this.title = title;
		this.courseId = courseId;
		this.startedAt = startedAt;
		this.endedAt = endedAt;
	}

	private static void validateSessionDate(final LocalDate startedAt, final LocalDate endedAt) {
		if (startedAt.isAfter(endedAt)) {
			throw new IllegalArgumentException("강의 종료일보다 강의 시작일이 늦을 수 없습니다.");
		}
	}
}
