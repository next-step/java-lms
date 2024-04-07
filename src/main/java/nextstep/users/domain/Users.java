package nextstep.users.domain;

import java.util.ArrayList;
import java.util.List;

public class Users {
  private final List<NsUser> users = new ArrayList<>();

  public Users() {

  }

  public Users(final List<NsUser> users) {
    this.users.addAll(users);
  }

  public void add(final NsUser user) {
    this.users.add(user);
  }

  public void addAll(final List<NsUser> users) {
    this.users.addAll(users);
  }

  public boolean greaterOrEqualTo(final int size) {
    return this.users.size() >= size;
  }

  public Integer size() {
    return this.users.size();
  }

  public boolean contains(final NsUser user) {
    return this.users.contains(user);
  }
}
