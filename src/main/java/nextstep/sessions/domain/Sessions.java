package nextstep.sessions.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Sessions {
  private final List<Session> values = new ArrayList<>();

  public void add(Session session) {
    values.add(session);
  }

  public List<Session> asList() {
    return Collections.unmodifiableList(values);
  }

  public int size() {
    return values.size();
  }

  public Stream<Session> stream() {
    return values.stream();
  }
}
