package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class Students {
  private final List<NsUser> students;

  private Students(List<NsUser> students) {
    this.students = students;
  }

  public static Students defaultOf() {
    return new Students(new ArrayList<>());
  }
  public void canAdd(int limitSize) {
    if (this.students.size() >= limitSize) {
      throw new IllegalArgumentException("수강인원이 가득찼습니다.");
    }
  }

  public void add(NsUser user) {
    students.add(user);
  }

  public void addWithLimitCount(NsUser user, Integer limitCount) {
    canAdd(limitCount);
    add(user);
  }

  public void addAll(List<NsUser> students) {
    students.addAll(students);
  }

  public Integer size() {
    return this.students.size();
  }

}
