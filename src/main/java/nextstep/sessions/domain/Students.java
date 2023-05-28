package nextstep.sessions.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nextstep.users.domain.NsUser;

public class Students {

	private final List<NsUser> nsUsers;

	public Students() {
		this(new ArrayList<>());
	}

	public Students(List<NsUser> nsUsers) {
		this.nsUsers = nsUsers;
	}

	public boolean contains(NsUser nsUser) {
		return this.nsUsers.contains(nsUser);
	}

	public void add(NsUser nsUser) {
		this.nsUsers.add(nsUser);
	}

	public boolean isFull(int capacity) {
		return this.nsUsers.size() >= capacity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Students students = (Students)o;
		return Objects.equals(nsUsers, students.nsUsers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nsUsers);
	}
}
