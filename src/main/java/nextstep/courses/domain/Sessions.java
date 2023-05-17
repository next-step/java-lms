package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {

  private final List<Session> sessions;

  public Sessions() {
    this.sessions = new ArrayList<>();
  }

  public void addSession(Session session) {
    sessions.add(session);
  }

  public int currentSessionCount() {
    return sessions.size();
  }
}
