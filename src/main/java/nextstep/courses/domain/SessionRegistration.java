package nextstep.courses.domain;

import nextstep.courses.domain.enums.PaidType;
import nextstep.users.domain.NsUser;

public class SessionRegistration {
	private final PaidType paidType;
	private final Tuition tuition;
	private final SessionCapacity maximumCapacity;
	private final Students students;

	public SessionRegistration(
		PaidType paidType, Tuition tuition,
		SessionCapacity maximumCapacity,Students students
	) {
		this.paidType = paidType;
		this.tuition = tuition;
		this.maximumCapacity = maximumCapacity;
		this.students = students;
	}

	public void valid(long amount) {
		validAmount(amount);
		validCapacity();
	}

	private void validAmount(long amount) {
		tuition.isEqual(amount);
	}

	private void validCapacity() {
		if (!paidType.isFree()) {
			maximumCapacity.isGreaterThan(students.number());
		}
	}

	public void register(NsUser nsUser) {
		students.add(nsUser);
	}
}
