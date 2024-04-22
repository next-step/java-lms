package nextstep.courses.domain;

public interface SessionType {
	boolean isMaximumNumberOfParticipantsLimited(int NumberOfParticipants);

	boolean isSamePaymentAndSessionPrice(int price);
}
