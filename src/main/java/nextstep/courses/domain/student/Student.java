package nextstep.courses.domain.student;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

public class Student {
	private Long id;

	private NsUser nsUser;

	private Session session;

	public Student(Long id, NsUser nsUser, Session session) {
		this.id = id;
		this.nsUser = nsUser;
		this.session = session;
	}
}
