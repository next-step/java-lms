package nextstep.courses.builder;

import nextstep.courses.domain.session.PaidSession;

public class PaidSessionBuilder extends SessionBuilder{
	private int maxNumberOfStudent = 10;

	private Long fee = 10000L;

	public PaidSessionBuilder maxNumberOfStudent(int maxNumberOfStudent) {
		this.maxNumberOfStudent = maxNumberOfStudent;
		return this;
	}

	public PaidSessionBuilder fee(Long fee) {
		this.fee = fee;
		return this;
	}

	public PaidSession build() {
		return new PaidSession(id, sessionState, numberOfStudent, sessionImage, startDate, endDate, maxNumberOfStudent, fee);
	}
}
