package nextstep.sessions.domain;

import java.util.HashSet;
import java.util.Set;

public class SessionRegistration {
  private int capacity;

  private SessionStatus status;

  private Students students;

  public SessionRegistration(int capacity) {
    this(capacity, SessionStatus.READY, new HashSet<>());
  }

  public SessionRegistration(int capacity, SessionStatus status, Set<Student> students) {
    this(capacity, status, new Students(students));
  }

  public SessionRegistration(int capacity, SessionStatus status, Students students) {
    this.capacity = capacity;
    this.status = status;
    this.students = students;
  }

  public void recruitStart() {
    this.status = SessionStatus.RECRUITING;
  }

  public void recruitEnd() {
    this.status = SessionStatus.END;
  }

  public void enrolment(Student student) {
    if (students.overFull(capacity)) {
      throw new IllegalStateException("수강인원이 초과되었습니다");
    }

    if (students.contains(student)) {
      throw new IllegalStateException("이미 수강신청한 사용자입니다");
    }

    SessionStatus.isRecruitingOrThrow(status);

    students.add(student);
  }

  public void validateInit() {
    if (capacity <= 0) {
      throw new IllegalArgumentException("수강인원 수는 1명 이상이어야 합니다");
    }
  }

  public int getCapacity() {
    return this.capacity;
  }

  public SessionStatus getStatus() {
    return this.status;
  }

  public Set<Student> getStudents() {
    return this.students.getStudents();
  }

  @Override
  public String toString() {
    return "SessionRegistration{" +
        "capacity=" + capacity +
        ", status=" + status +
        ", students=" + students +
        '}';
  }
}
