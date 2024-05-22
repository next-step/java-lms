package nextstep.courses.builder;

import nextstep.courses.entity.SessionEntity;

import java.time.LocalDateTime;

public class SessionEntityBuilder {
	private Long id = 0L;

	private Long courseId = 0L;

	private String state = "RECRUITING";

	private Long imageId = 0L;

	private LocalDateTime startDate = LocalDateTime.of(2024, 4, 20, 0, 0, 0);

	private LocalDateTime endDate = LocalDateTime.of(2024, 5, 20, 0, 0, 0);

	private int numberOfStudent = 0;

	private int maxNumberOfStudent = 10;

	private Long fee = 10000L;

	public SessionEntity build() {
		return new SessionEntity(id, courseId, state, imageId, startDate, endDate, numberOfStudent, maxNumberOfStudent, fee);
	}
}
