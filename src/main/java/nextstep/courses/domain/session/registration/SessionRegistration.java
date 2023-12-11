package nextstep.courses.domain.session.registration;

import nextstep.courses.domain.enums.ApprovalStatus;
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

	public void validateAmount(long amount) {
		tuition.validateEqual(amount);
	}

	public void validateCapacity() {
		if (!paidType.isFree()) {
			maximumCapacity.validateGreaterThan(students.getApprovalNumber());
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
