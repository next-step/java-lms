package nextstep.session.domain;

import nextstep.courses.domain.Course;

public class FreeSession extends Session {
	public FreeSession(Course course, ImageInfo imageInfo, Period period, int maximumNumberOfParticipants) {
		super(course, imageInfo, period, maximumNumberOfParticipants, 0L);
	}

}
