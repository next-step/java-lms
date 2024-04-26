package nextstep.session.domain;

import nextstep.courses.domain.Course;

public class PaidSession extends Session {

	private PaidSessionBody paidSessionBody;

	public PaidSession(Course course, ImageInfo imageInfo, Period period, PaidSessionBody paidSessionBody) {
		super(course, imageInfo, period);
		this.paidSessionBody = paidSessionBody;
	}

	@Override
	public boolean isMaximumNumberOfParticipantsLimited(int numberOfParticipants) {
		return paidSessionBody.isMaximumNumberOfParticipantsLimited(numberOfParticipants);
	}

	@Override
	public boolean isSamePaymentAndSessionPrice(int payment) {
		return paidSessionBody.isSamePaymentAndSessionPrice(payment);
	}

}
