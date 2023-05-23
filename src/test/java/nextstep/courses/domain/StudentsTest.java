package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class StudentsTest {

  public static Students ofNoUsersYet() {
    return new Students(new MaxEnrollment(10));
  }

  public static Students ofLeftFewSeats() {
    return new Students(new MaxEnrollment(10)){
      {
        add(NsUserTest.JAVAJIGI);
        add(NsUserTest.SANJIGI);
      }
    };
  }

  public static Students ofFullUsers() {
    return new Students(new MaxEnrollment(2)){
      {
        add(NsUserTest.JAVAJIGI);
        add(NsUserTest.SANJIGI);
      }
    };
  }

  public static Students ofLeftOneSeatUsers() {
    return new Students(new MaxEnrollment(2)){
      {
        add(NsUserTest.JAVAJIGI);
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

    assertThatThrownBy(() -> students.add(NsUserTest.JAVAJIGI))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("최대 인원수를 넘어서 수강 신청할 수 없습니다.");
  }

  @Test
  void 수강_인원이_가득차지_않은_상태에서_수강생을_추가하는_경우_예외_발생_안함(){
    Students students = ofLeftFewSeats();

    assertThatCode(() -> students.add(NsUserTest.JAVAJIGI))
        .doesNotThrowAnyException();
  }

  @Test
  void 학생을_목록에_등록하면_학생등록목록_사이즈_1_증가(){
    Students students = ofNoUsersYet();

    students.add(NsUserTest.JAVAJIGI);

    assertThat(students.size()).isEqualTo(1);
  }



}