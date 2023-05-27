package nextstep.sessions.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {

	private final List<Session> sessions;

	public Sessions() {
		this(new ArrayList<>());
	}

	public Sessions(List<Session> sessions) {
		this.sessions = sessions;
	}
}
