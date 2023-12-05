package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class NsUsers {
  List<NsUser> students;

  private NsUsers(List<NsUser> students) {
    this.students = students;
  }

  public static NsUsers defaultOf() {
    return new NsUsers(new ArrayList<>());
  }

  public void add(NsUser user) {
    students.add(user);
  }

  public void addAll(List<NsUser> students) {
    students.addAll(students);
  }

  public Integer size() {
    return this.students.size();
  }
}
