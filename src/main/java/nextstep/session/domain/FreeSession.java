package nextstep.session.domain;

import nextstep.courses.domain.Course;

public class FreeSession extends Session {
	public FreeSession(Course course, ImageInfo imageInfo, Period period, int maximumNumberOfParticipants) {
		super(course, imageInfo, period, maximumNumberOfParticipants);
	}

	@Override
	public boolean isSamePaymentAndSessionPrice(int price) {
		return true;
	}

}
