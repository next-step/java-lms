package nextstep.courses.domain.session;

import exception.LmsException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import nextstep.courses.exception.SessionExceptionCode;
import nextstep.users.domain.NsUser;

public class SessionStudents {

  private final List<SessionStudent> students;
  private final Map<Long, SessionStudent> studentMap;

  public SessionStudents(List<SessionStudent> students) {
    this.students = students;
    this.studentMap = students.stream()
        .collect(Collectors.toMap(SessionStudent::getNsUserId, Function.identity()));
  }

  public boolean contains(Long nsUserId) {
    SessionStudent sessionStudent = studentMap.get(nsUserId);
    return sessionStudent != null;
  }

  public int getCurrentStudentCount() {
    return students.size();
  }

  public void addStudent(SessionStudent student) {
    if (this.contains(student.getNsUserId())) {
      throw new LmsException(SessionExceptionCode.STUDENT_ALREADY_REGISTERED);
    }

    students.add(student);
  }
}
