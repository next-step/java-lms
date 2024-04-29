package nextstep.session.domain;

public class PaidSessionBody {

	private final long sessionPrice;
	private final int maximumNumberOfParticipants;

	public PaidSessionBody(long sessionPrice, int maximumNumberOfParticipants) {
		this.sessionPrice = sessionPrice;
		this.maximumNumberOfParticipants = maximumNumberOfParticipants;
	}

	public boolean isMaximumNumberOfParticipantsLimited(int numberOfParticipants) {
		return numberOfParticipants <= maximumNumberOfParticipants;
	}

	public boolean isSamePaymentAndSessionPrice(int payment) {
		return payment == sessionPrice;
	}

}
