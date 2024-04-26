package nextstep.courses.domain;

import java.time.LocalDateTime;

abstract class Session {
	private Long id;
	private SessionStatus sessionStatus;
	private Course course;

	private ImageInfo imageType;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	public Session(Course course, ImageInfo imageInfo, LocalDateTime startDate, LocalDateTime endDate) {
		this(0L, SessionStatus.PREPARING, course, imageInfo, startDate, endDate);
	}

	public Session(Long id, SessionStatus sessionStatus, Course course, ImageInfo imageInfo, LocalDateTime startDate, LocalDateTime endDate) {
		this.id = id;
		this.sessionStatus = sessionStatus;
		this.course = course;
		this.imageType = imageInfo;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	abstract boolean isMaximumNumberOfParticipantsLimited(int NumberOfParticipants);

	abstract boolean isSamePaymentAndSessionPrice(int price);

	public boolean isSessionRegister() {
		return sessionStatus == SessionStatus.PREPARING;
	}

}
