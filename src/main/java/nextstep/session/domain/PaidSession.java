package nextstep.session.domain;

import nextstep.courses.domain.Course;

public class PaidSession extends Session {
	private final long sessionPrice;

	public PaidSession(Course course, ImageInfo imageInfo, Period period, long sessionPrice, int maximumNumberOfParticipants) {
		super(course, imageInfo, period, maximumNumberOfParticipants);
		this.sessionPrice = sessionPrice;
	}

	@Override
	public boolean isSamePaymentAndSessionPrice(int payment) {
		return payment == sessionPrice;
	}

}
