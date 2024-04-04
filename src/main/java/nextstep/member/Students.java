package nextstep.member;

import java.util.ArrayList;
import java.util.List;

public class Students {
  private final List<Student> students = new ArrayList<>();

  public Students(final List<Student> students) {
    this.students.addAll(students);
  }

  public void add(final Student student) {
    this.students.add(student);
  }

  public boolean greaterOrEqualTo(final int size) {
    return this.students.size() >= size;
  }

  public Integer size() {
    return this.students.size();
  }
}
