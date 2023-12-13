package nextstep.courses.domain;

import java.util.List;

public class NsUserSessions {
	private List<NsUserSession> nsUserSessions;

	public NsUserSessions(List<NsUserSession> nsUserSessions) {
		this.nsUserSessions = nsUserSessions;
	}

	public int userNumber() {
		return nsUserSessions.size();
	}
}
