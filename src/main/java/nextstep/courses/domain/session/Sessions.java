package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;

import nextstep.courses.domain.session.Session;

public class Sessions {
	private final List<Session> sessions;

	public Sessions () {
		this.sessions = new ArrayList<>();
	}

	public Sessions(List<Session> sessions) {
		this.sessions = sessions;
	}
}
