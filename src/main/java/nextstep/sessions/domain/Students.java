package nextstep.sessions.domain;

import java.util.Set;
import nextstep.users.domain.NsUser;

public class Students {

  private Set<Student> students;


  public Students(Set<Student> students) {
    this.students = students;
  }

  public boolean contains(Session session, NsUser user) {
    return students.stream()
        .filter(student -> student.isTaking(session, user))
        .findAny()
        .isPresent();
  }

  public Set<Student> getStudents() {
    return this.students;
  }

  public void add(Session session, NsUser user) {
    this.students.add(new Student(session, user));
  }

  public int size() {
    return students.size();
  }

  @Override
  public String toString() {
    return "Students{" +
        "students=" + students +
        '}';
  }
}
