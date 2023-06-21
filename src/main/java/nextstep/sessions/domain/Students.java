package nextstep.sessions.domain;

import java.util.Set;

public class Students {

  private Set<Student> students;

  public Students(Set<Student> students) {
    this.students = students;
  }

  public boolean overFull(int capacity) {
    long count = students.stream().filter(student -> !student.isRejected()).count();
    return count >= capacity;
  }

  public boolean contains(Student student) {
    return students.stream()
        .filter(s -> s.isTaking(student))
        .findAny()
        .isPresent();
  }

  public Set<Student> getStudents() {
    return this.students;
  }

  public void add(Student student) {
    this.students.add(student);
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
