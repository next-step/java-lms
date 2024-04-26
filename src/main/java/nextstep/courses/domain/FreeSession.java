package nextstep.courses.domain;

import java.time.LocalDateTime;

public class FreeSession extends Session {

	public FreeSession(Course course, ImageInfo imageInfo, Period period) {
		super(course, imageInfo, period);
	}

	@Override
	public boolean isMaximumNumberOfParticipantsLimited(int NumberOfParticipants) {
		return true;
	}

	@Override
	public boolean isSamePaymentAndSessionPrice(int price) {
		return true;
	}

}
