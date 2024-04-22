package nextstep.courses.domain;

public class PaidSession implements SessionType {
	private long sessionPrice;
	private int maximumNumberOfParticipants;

	public PaidSession(long sessionPrice, int maximumNumberOfParticipants) {
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
