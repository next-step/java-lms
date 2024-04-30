package nextstep.session.domain;

import nextstep.courses.domain.Course;

public class PaidSession extends Session {

	public PaidSession(Course course, ImageInfo imageInfo, Period period, long sessionPrice, int maximumNumberOfParticipants) {
		super(course, imageInfo, period, maximumNumberOfParticipants, sessionPrice);
	}

}
