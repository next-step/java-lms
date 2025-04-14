package nextstep.users.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NsUsers {
  private final List<NsUser> values = new ArrayList<>();

  public void add(NsUser user) {
    values.add(user);
  }

  public int count() {
    return values.size();
  }

  public List<NsUser> asList() {
    return Collections.unmodifiableList(values);
  }

  public boolean contains(NsUser user) {
    return values.contains(user);
  }
}
