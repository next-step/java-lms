package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nextstep.users.domain.NsUser;

public class SessionStudent {
  List<NsUser> students;
  private Integer maxStudent;

  public SessionStudent() {
    this(Integer.MAX_VALUE);
  }
  public SessionStudent(Integer maxStudent) {
    this.maxStudent = maxStudent;
    this.students = new ArrayList<>();
  }

  public SessionStudent(List<NsUser> students) {
    this.students = students;
  }

  public  boolean canAcceptNewStudent() {
    return size() < maxStudent;
  }

  public void add(NsUser newStudent) {
    students.add(newStudent);
  }

  private Integer size() {
    return students.size();
  }

  public List<Long> getIds() {
    return students.stream().map(NsUser::getId).collect(Collectors.toList());
  }

  public List<NsUser> getStudents(){
    return this.students;
  }
}
