package nextstep.session.domain;

import nextstep.courses.domain.Course;

public abstract class Session {
	private Long id;
	private Course course;
	private ImageInfo imageType;
	private Period period;
	private Enrollment enrollment;

	public Session(Course course, ImageInfo imageInfo, Period period, int maximumNumberOfParticipants) {
		this(0L, course, imageInfo, period, new Enrollment(maximumNumberOfParticipants));
	}

	public Session(Long id, Course course, ImageInfo imageInfo, Period period, Enrollment enrollment) {
		this.id = id;
		this.course = course;
		this.imageType = imageInfo;
		this.period = period;
		this.enrollment = enrollment;
	}

	abstract boolean isSamePaymentAndSessionPrice(int price);

}
