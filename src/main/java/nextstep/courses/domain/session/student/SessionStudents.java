package nextstep.courses.domain.session.student;

import exception.LmsException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import nextstep.courses.exception.SessionExceptionCode;
import org.springframework.util.CollectionUtils;

public class SessionStudents {

  private final Map<Long, SessionStudent> studentMap;

  public SessionStudents(List<SessionStudent> students) {
    this.studentMap = getAsMap(students);
  }

  public boolean contains(Long nsUserId) {
    SessionStudent sessionStudent = studentMap.get(nsUserId);
    return sessionStudent != null;
  }

  public int getCurrentStudentCount() {
    return studentMap.size();
  }

  public void addStudent(SessionStudent student) {
    if (this.contains(student.getNsUserId())) {
      throw new LmsException(SessionExceptionCode.STUDENT_ALREADY_REGISTERED);
    }

    studentMap.put(student.getNsUserId(), student);
  }

  public SessionStudent getStudent(Long nsUserId) {
    SessionStudent sessionStudent = studentMap.get(nsUserId);
    if (sessionStudent == null) {
      throw new LmsException(SessionExceptionCode.STUDENT_NOT_FOUND);
    }
    return sessionStudent;
  }

  private Map<Long, SessionStudent> getAsMap(List<SessionStudent> students) {
    if (CollectionUtils.isEmpty(students)) {
      return new HashMap<>();
    }

    return students.stream()
        .collect(Collectors.toMap(SessionStudent::getNsUserId, Function.identity()));
  }
}
