package nextstep.courses.domain.session.registration;

import java.util.ArrayList;
import java.util.List;

import nextstep.courses.domain.enums.ApprovalStatus;
import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

public class Students {
	private final List<Registration> registrations;

	public Students() {
		this.registrations = new ArrayList<>();
	}

	public Students(List<Registration> registrations) {
		this.registrations = registrations;
	}

	public void add(Session session, NsUser nsUser) {
		registrations.add(new Registration(nsUser, session, ApprovalStatus.WAITING));
	}

	public int number() {
		return registrations.size();
	}
	public int getApprovalNumber() {
		return (int) registrations.stream()
			.filter(Registration::isApproval)
			.count();
	}

	public List<Registration> getRegistrations() {
		return registrations;
	}
}
