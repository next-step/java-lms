package nextstep.courses.domain.student;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {
	private List<Student> students;

	public Students() {
		this(new ArrayList<>());
	}

	public Students(List<Student> students) {
		this.students = students;
	}

	public void add(NsUser nsUser, Session session) {
		students.add(new Student(nsUser, session));
	}
}
