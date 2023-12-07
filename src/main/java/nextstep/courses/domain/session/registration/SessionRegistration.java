package nextstep.courses.domain.session.registration;

import nextstep.courses.domain.enums.PaidType;
import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

public class SessionRegistration {
	private final PaidType paidType;
	private final Tuition tuition;
	private final SessionCapacity maximumCapacity;
	private final Students students;

	public SessionRegistration(
		PaidType paidType, Tuition tuition,
		SessionCapacity maximumCapacity, Students students
	) {
		this.paidType = paidType;
		this.tuition = tuition;
		this.maximumCapacity = maximumCapacity;
		this.students = students;
	}

	public void validate(long amount) {
		validateAmount(amount);
		validateCapacity();
	}

	private void validateAmount(long amount) {
		tuition.validateEqual(amount);
	}

	private void validateCapacity() {
		if (!paidType.isFree()) {
			maximumCapacity.validateGreaterThan(students.number());
		}
	}

	public void register(NsUser nsUser, Session session) {
		students.add(session, nsUser);
	}

	public PaidType getPaidType() {
		return paidType;
	}

	public Tuition getTuition() {
		return tuition;
	}

	public SessionCapacity getMaximumCapacity() {
		return maximumCapacity;
	}

	public Students getStudents() {
		return students;
	}
}
