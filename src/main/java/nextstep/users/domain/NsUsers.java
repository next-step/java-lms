package nextstep.users.domain;

import java.util.ArrayList;
import java.util.List;

public class NsUsers {
	private List<NsUser> nsUsers;

	public NsUsers() {
		this.nsUsers = new ArrayList<>();
	}

	public void addStudent(NsUser student) {
		this.nsUsers.add(student);
	}

	public int getNumberOfStudent() {
		return this.nsUsers.size();
	}

}
