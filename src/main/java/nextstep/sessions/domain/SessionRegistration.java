package nextstep.sessions.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nextstep.users.domain.NsUserGroup;
import nextstep.users.domain.NsUserNsUserGroup;

public class SessionRegistration {
  private int capacity;
  private SessionRecruitingStatus recruitingStatus;
  private SessionProgressStatus progressStatus;
  private Students students;
  private NsUserGroup nsUserGroup;

  public SessionRegistration(int capacity, NsUserGroup nsUserGroup) {
    this(capacity, SessionRecruitingStatus.NOTHING, SessionProgressStatus.READY, new Students(new HashSet<>()), nsUserGroup);
  }

  public SessionRegistration(int capacity, SessionRecruitingStatus recruitingStatus, SessionProgressStatus progressStatus, Students students, NsUserGroup nsUserGroup) {
    this.capacity = capacity;
    this.recruitingStatus = recruitingStatus;
    this.progressStatus = progressStatus;
    this.students = students;
    this.nsUserGroup = nsUserGroup;
  }

  public void recruitStart() {
    this.recruitingStatus = SessionRecruitingStatus.RECRUITING;
  }

  public void recruitEnd() {
    this.recruitingStatus = SessionRecruitingStatus.NOTHING;
  }

  public void enrolment(Student student) {
    if (students.overFull(capacity)) {
      throw new IllegalStateException("수강인원이 초과되었습니다");
    }

    if (students.contains(student)) {
      throw new IllegalStateException("이미 수강신청한 사용자입니다");
    }

    if (!progressStatus.isApplicable()) {
      throw new IllegalStateException("강의가 종료된 상태입니다");
    }

    SessionRecruitingStatus.isRecruitingOrThrow(recruitingStatus);

    students.add(student);
  }

  public void validateInit() {
    if (capacity <= 0) {
      throw new IllegalArgumentException("수강인원 수는 1명 이상이어야 합니다");
    }
  }

  public void accept(List<NsUserNsUserGroup> nsUserNsUserGroups, Student student) {
    nsUserNsUserGroups.stream().filter(group -> group.getNsUserGroupId().equals(this.nsUserGroup.getId()))
        .findAny()
        .orElseThrow(() -> new IllegalStateException("강의에 선발된 인원만 수강신청이 가능합니다"));

    student.accept();
  }

  public int getCapacity() {
    return this.capacity;
  }

  public SessionRecruitingStatus getRecruitingStatus() {
    return this.recruitingStatus;
  }

  public SessionProgressStatus getProgressStatus() {
    return this.progressStatus;
  }

  public Set<Student> getStudents() {
    return this.students.getStudents();
  }

  public NsUserGroup getNsUserGroup() {
    return nsUserGroup;
  }

  @Override
  public String toString() {
    return "SessionRegistration{" +
        "capacity=" + capacity +
        ", status=" + recruitingStatus +
        ", students=" + students +
        '}';
  }
}
