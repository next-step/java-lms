package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

/**
 * 최대 인원수를 넘어서 수강 신청할 수 없다.
 */
public class Students {

  private final List<Student> students;
  private final MaxEnrollment maxEnrollment;

  public Students(List<Student> students, MaxEnrollment maxEnrollment) {
    this.students = students;
    this.maxEnrollment = maxEnrollment;
  }

  public Students(MaxEnrollment maxEnrollment) {
    this(new ArrayList<>(), maxEnrollment);
  }

  public boolean isEmpty() {
    return students.isEmpty();
  }


  /**
   * 현재 Session Students에는 대기중, 승인, 취소된 유저 모두 포함되어 있습니다.
   * 매번 수강 신청할때마다 , 승인된 유저 수를 세서 최대 인원수를 넘어서면 더 이상 수강대기 상태라도
   * 수강 신청할 수 없는 상태입니다.
   */
  public void add(Student student) {
    if (isFull()) {
      throw new IllegalArgumentException("최대 인원수를 넘어서 수강 신청할 수 없습니다.");
    }
    students.add(student);
  }

  private boolean isFull() {
    return maxEnrollment.isFull(approvedStudentCount());
  }

  private int approvedStudentCount() {
    return (int) students.stream()
        .filter(Student::isApproved)
        .count();
  }

  public int size() {
    return students.size();
  }

  public List<Student> listOfStudents() {
    return students;
  }

  public void approve(NsUser user) {
    if (isFull()) {
      throw new IllegalArgumentException("최대 인원수를 넘어서 더 이상 수강 승인을 할 수 없습니다.");
    }

    for(int index = 0; index < students.size(); index++) {
      if (students.get(index).isWaiting() && students.get(index).equals(new Student(user))) {
        students.set(index, students.get(index).approved(ApproveStatus.APPROVED));
        return;
      }
    }
  }

  public void reject(NsUser user) {
    for(int index = 0; index < students.size(); index++) {
      if (students.get(index).isWaiting() && students.get(index).equals(new Student(user))) {
        students.set(index, students.get(index).rejected(ApproveStatus.REJECTED));
        return;
      }
    }
  }
}
