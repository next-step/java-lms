package nextstep.courses.domain.session;

import exception.LmsException;
import java.util.List;
import nextstep.courses.exception.SessionExceptionCode;
import nextstep.users.domain.NsUser;

public class SessionStudents {

  private final List<SessionStudent> students;
  public SessionStudents(List<SessionStudent> students) {
    this.students = students;
  }

  public boolean contains(NsUser nsUser) {
    return students.stream()
        .filter(SessionStudent::isNotCancelled)
        .anyMatch(student -> student.isUserOf(nsUser));
  }

  public int getCurrentStudentCnt() {
    return students.size();
  }

  public void addStudent(SessionStudent student) {
    if (this.contains(student.getNsUser())) {
      throw new LmsException(SessionExceptionCode.STUDENT_ALREADY_REGISTERED);
    }

    students.add(student);
  }
}
