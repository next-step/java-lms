package nextstep.courses.domain.session.registration;

import java.util.ArrayList;
import java.util.List;

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
		registrations.add(new Registration(session.getId(), nsUser.getId()));
	}

	public void addAll(List<Registration> registrations) {
		this.registrations.addAll(registrations);
	}

	public int number() {
		return registrations.size();
	}

	public List<Registration> getRegistrations() {
		return registrations;
	}
}
