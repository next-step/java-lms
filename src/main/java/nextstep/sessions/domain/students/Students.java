package nextstep.sessions.domain.students;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Students {

  private Set<Student> students;

  public Students(List<Student> students) {
    this(new HashSet<>(students));
  }

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

  public Stream<Student> stream() {
    return this.students.stream();
  }

  @Override
  public String toString() {
    return "Students{" +
        "students=" + students +
        '}';
  }
}
