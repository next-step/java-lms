package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

import nextstep.users.domain.NsUser;

public class Students {
	private final List<NsUser> students;

	public Students() {
		this.students = new ArrayList<>();
	}

	public Students(List<NsUser> students) {
		this.students = students;
	}

	public void add(NsUser nsUser) {
		students.add(nsUser);
	}

	public int number() {
		return students.size();
	}
}
