package nextstep.sessions.domain;

import java.util.List;

public class Sessions {
	private List<Session> sessions;

	public Sessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public int size() {
		return sessions.size();
	}

	public void add(final Session session) {
		sessions.add(session);
	}
}
