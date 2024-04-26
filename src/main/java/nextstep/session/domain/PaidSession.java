package nextstep.session.domain;

import nextstep.courses.domain.Course;

public class PaidSession extends Session {
	private long sessionPrice;
	private int maximumNumberOfParticipants;

	public PaidSession(Course course, ImageInfo imageInfo, Period period, long sessionPrice, int maximumNumberOfParticipants) {
		super(course, imageInfo, period);
		this.sessionPrice = sessionPrice;
		this.maximumNumberOfParticipants = maximumNumberOfParticipants;
	}

	@Override
	public boolean isMaximumNumberOfParticipantsLimited(int NumberOfParticipants) {
		return NumberOfParticipants <= maximumNumberOfParticipants;
	}

	@Override
	public boolean isSamePaymentAndSessionPrice(int payment) {
		return payment == sessionPrice;
	}

}
