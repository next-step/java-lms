package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class StudentsTest {

  public static Students ofNoUsersYet() {
    return new Students(new MaxEnrollment(10));
  }

  public static Students ofLeftFewSeats() {
    return new Students(new MaxEnrollment(10)){
      {
        add(new Student(NsUserTest.JAVAJIGI, ApproveStatus.APPROVED));
        add(new Student(NsUserTest.SANJIGI,  ApproveStatus.APPROVED));
      }
    };
  }

  public static Students ofFullUsers() {
    return new Students(new MaxEnrollment(2)){
      {
        add(new Student(NsUserTest.JAVAJIGI, ApproveStatus.APPROVED));
        add(new Student(NsUserTest.SANJIGI,  ApproveStatus.APPROVED));
      }
    };
  }

  public static Students ofLeftOneSeatWithOneWaiting() {
    return new Students(new MaxEnrollment(3)){
      {
        add(new Student(NsUserTest.JAVAJIGI, ApproveStatus.APPROVED));
        add(new Student(NsUserTest.SANJIGI,  ApproveStatus.APPROVED));
        add(new Student(NsUserTest.SOOCHAN,  ApproveStatus.WAITING));
      }
    };
  }

  public static Students ofLeftOneSeatUsers() {
    return new Students(new MaxEnrollment(2)){
      {
        add(new Student(NsUserTest.JAVAJIGI, ApproveStatus.APPROVED));
      }
    };
  }


  @Test
  void 수강_인원이_비어있으면_isEmpty_true_반환_테스트() {
    Students students = ofNoUsersYet();

    boolean isEmpty = students.isEmpty();

    assertThat(isEmpty).isTrue();
  }

  @Test
  void 수강_인원이_가득차있으면_isEmpty_false_반환_테스트() {
    Students students = ofFullUsers();

    boolean isEmpty = students.isEmpty();

    assertThat(isEmpty).isFalse();
  }

  @Test
  void 수강_인원이_가득차_있는_상태에서_수강생을_추가하는_경우_예외_발생(){
    Students students = ofFullUsers();

    assertThatThrownBy(() -> students.add(new Student(NsUserTest.JAVAJIGI, ApproveStatus.APPROVED)))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("최대 인원수를 넘어서 수강 신청할 수 없습니다.");
  }

  @Test
  void 수강_인원이_가득차지_않은_상태에서_수강생을_추가하는_경우_예외_발생_안함(){
    Students students = ofLeftFewSeats();

    assertThatCode(() -> students.add(new Student(NsUserTest.JAVAJIGI, ApproveStatus.APPROVED)))
        .doesNotThrowAnyException();
  }

  @Test
  void 학생을_목록에_등록하면_학생등록목록_사이즈_1_증가(){
    Students students = ofNoUsersYet();

    students.add(new Student(NsUserTest.JAVAJIGI, ApproveStatus.APPROVED));

    assertThat(students.size()).isEqualTo(1);
  }

  @Test
  void 특정_강의에_2명_수강신청_승인된_강의에서_수강신청시_기본적으로_대기상태_확인() {
    Students students = ofLeftFewSeats();

    students.add(new Student(NsUserTest.JAVAJIGI, ApproveStatus.WAITING));

    int waitingStudents = (int) students.listOfStudents()
        .stream()
        .filter(Student::isWaiting).count();

    assertThat(waitingStudents).isEqualTo(1);
  }

  @Test
  void 특정_강의에_수강승인된_학생으로_가득찰_경우_대기상태도_접수불가() {
    Students students = ofFullUsers();

    assertThatThrownBy(() -> students.add(new Student(NsUserTest.JAVAJIGI, ApproveStatus.WAITING)))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("최대 인원수를 넘어서 수강 신청할 수 없습니다.");
  }

  @Test
  void 수강승인으로_가득찬_강의에_대해_더_이상_수강신청_불가() {
    Students students = ofFullUsers();

    assertThatThrownBy(() -> students.approve(NsUserTest.SOOCHAN))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("최대 인원수를 넘어서 더 이상 수강 승인을 할 수 없습니다.");
  }


  @Test
  void 마지막_남은_한자리에_대해_수강승인을_할_경우_대기상태_학생이_수강승인_상태로_변경() {
    Students students = ofLeftOneSeatWithOneWaiting();

    students.approve(NsUserTest.SOOCHAN);

    int approvedStudents = (int) students.listOfStudents()
        .stream()
        .filter(Student::isApproved).count();

    assertAll(
        () -> assertThat(approvedStudents).isEqualTo(3),
        () -> assertThat(students.listOfStudents().get(2).isApproved()).isTrue()
    );
  }

  @Test
  void 마지막_남은_한자리가_있지만_선발되지_못한_대기학생을_수강거절_상태로_변경() {
    Students students = ofLeftOneSeatWithOneWaiting();

    students.reject(NsUserTest.SOOCHAN);

    int rejectedStudents = (int) students.listOfStudents()
        .stream()
        .filter(Student::isRejected).count();

    assertAll(
        () -> assertThat(rejectedStudents).isEqualTo(1),
        () -> assertThat(students.listOfStudents().get(2).isRejected()).isTrue()
    );
  }


}