package nextstep.courses.domain;

import java.time.LocalDateTime;

public class FreeSession extends Session {

	public FreeSession(Course course, ImageInfo imageInfo, LocalDateTime startDate, LocalDateTime endDate) {
		super(course, imageInfo, startDate, endDate);
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
