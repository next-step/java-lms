package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.courses.DuplicatedException;

public class Sessions {

  private List<Session> sessions = new ArrayList<>();

  public Sessions() {
  }

  public void addSession(Session session) {
    validateSession(session);
    sessions.add(session);
  }

  private void validateSession(Session session) {
    if (hasSession(session)) {
      throw new DuplicatedException("해당 기수에 중복되는 강의가 존재합니다.");
    }
  }

  public boolean hasSession(Session session) {
    return sessions.contains(session);
  }
}
