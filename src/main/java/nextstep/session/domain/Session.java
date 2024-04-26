package nextstep.session.domain;

import nextstep.courses.domain.Course;

public abstract class Session {
	private Long id;
	private SessionStatus sessionStatus;
	private Course course;
	private ImageInfo imageType;
	private Period period;

	public Session(Course course, ImageInfo imageInfo, Period period) {
		this(0L, SessionStatus.PREPARING, course, imageInfo, period);
	}

	public Session(Long id, SessionStatus sessionStatus, Course course, ImageInfo imageInfo, Period period) {
		this.id = id;
		this.sessionStatus = sessionStatus;
		this.course = course;
		this.imageType = imageInfo;
		this.period = period;
	}

	abstract boolean isMaximumNumberOfParticipantsLimited(int NumberOfParticipants);

	abstract boolean isSamePaymentAndSessionPrice(int price);

	public boolean isSessionRegister() {
		return sessionStatus == SessionStatus.PREPARING;
	}

}
