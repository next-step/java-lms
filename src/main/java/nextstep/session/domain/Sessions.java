package nextstep.session.domain;

import java.util.List;

public class Sessions {

  private final List<Session> sessions;

  public Sessions(List<Session> sessions) {
    this.sessions = sessions;
  }

  public void mapping(Long courseId) {
    sessions.forEach(session -> session.mapping(courseId));
  }
}
