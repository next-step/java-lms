package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
	private final List<Session> sessions;

	public Sessions () {
		this.sessions = new ArrayList<>();
	}

	public Sessions(List<Session> sessions) {
		this.sessions = sessions;
	}
}
