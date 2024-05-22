package nextstep.session.domain;

import nextstep.courses.domain.Course;

import java.time.LocalDateTime;

public class Session {
	private long id;
	private long courseId;
	private ImageInfo imageType;
	private Period period;
	private Enrollment enrollment;

	public Session(long courseId, ImageInfo imageInfo, Period period, int maximumNumberOfParticipants, long sessionPrice) {
		this(0L, courseId, imageInfo, period, new Enrollment(maximumNumberOfParticipants, sessionPrice));
	}

	public Session(long id, long courseId, ImageInfo imageInfo, Period period, Enrollment enrollment) {
		this.id = id;
		this.courseId = courseId;
		this.imageType = imageInfo;
		this.period = period;
		this.enrollment = enrollment;
	}

	public Session(long id, long courseId, LocalDateTime startDate, LocalDateTime endDate) {
		this.id = id;
		this.courseId = courseId;
		this.period = new Period(startDate,endDate);
	}

	public Period getPeriod() {
		return period;
	}

	public long getId() {
		return id;
	}

	public long getCourseId() {
		return courseId;
	}

	@Override
	public String toString() {
		return "Session{" +
				"id=" + id +
				", courseId=" + courseId +
				", imageType=" + imageType +
				", period=" + period +
				", enrollment=" + enrollment +
				'}';
	}

}
