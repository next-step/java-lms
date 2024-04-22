package nextstep.courses.domain;

public class FreeSession implements SessionType {
	@Override
	public boolean isMaximumNumberOfParticipantsLimited(int NumberOfParticipants) {
		return true;
	}

	@Override
	public boolean isSamePaymentAndSessionPrice(int price) {
		return true;
	}

}
