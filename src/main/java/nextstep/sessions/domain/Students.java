package nextstep.sessions.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nextstep.sessions.exception.CapacityNumberException;
import nextstep.users.domain.NsUser;

public class Students {

	private Long courseId;

	private Long sessionId;

	private final int capacity;

	private final List<NsUser> nsUsers;

	public Students(int capacity) {
		this(capacity, new ArrayList<>());
	}

	public Students(int capacity, List<NsUser> nsUsers) {
		if (capacity < 0) {
			throw new CapacityNumberException("최대 수강 인원은 음수일 수 없습니다.");
		}
		this.capacity = capacity;
		this.nsUsers = nsUsers;
	}

	public boolean contains(NsUser nsUser) {
		return this.nsUsers.contains(nsUser);
	}

	public void add(NsUser nsUser) {
		this.nsUsers.add(nsUser);
	}

	public boolean isFull() {
		return this.nsUsers.size() >= this.capacity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Students students = (Students)o;
		return capacity == students.capacity && Objects.equals(courseId, students.courseId) && Objects.equals(sessionId, students.sessionId) && Objects.equals(nsUsers, students.nsUsers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseId, sessionId, capacity, nsUsers);
	}
}
