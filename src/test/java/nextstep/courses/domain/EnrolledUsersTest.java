package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class EnrolledUsersTest {

  public static EnrolledUsers ofNoUsersYet() {
    return new EnrolledUsers(new MaxEnrollment(10));
  }

  public static EnrolledUsers ofLeftFewSeats() {
    return new EnrolledUsers(new MaxEnrollment(10)){
      {
        add(NsUserTest.JAVAJIGI);
        add(NsUserTest.SANJIGI);
      }
    };
  }

  public static EnrolledUsers ofFullUsers() {
    return new EnrolledUsers(new MaxEnrollment(2)){
      {
        add(NsUserTest.JAVAJIGI);
        add(NsUserTest.SANJIGI);
      }
    };
  }

  public static EnrolledUsers ofLeftOneSeatUsers() {
    return new EnrolledUsers(new MaxEnrollment(2)){
      {
        add(NsUserTest.JAVAJIGI);
      }
    };
  }


  @Test
  void 수강_인원이_비어있으면_isEmpty_true_반환_테스트() {
    EnrolledUsers enrolledUsers = ofNoUsersYet();

    boolean isEmpty = enrolledUsers.isEmpty();

    assertThat(isEmpty).isTrue();
  }

  @Test
  void 수강_인원이_가득차있으면_isEmpty_false_반환_테스트() {
    EnrolledUsers enrolledUsers = ofFullUsers();

    boolean isEmpty = enrolledUsers.isEmpty();

    assertThat(isEmpty).isFalse();
  }

  @Test
  void 수강_인원이_가득차_있는_상태에서_수강생을_추가하는_경우_예외_발생(){
    EnrolledUsers enrolledUsers = ofFullUsers();

    assertThatThrownBy(() -> enrolledUsers.add(NsUserTest.JAVAJIGI))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("최대 인원수를 넘어서 수강 신청할 수 없습니다.");
  }

  @Test
  void 수강_인원이_가득차지_않은_상태에서_수강생을_추가하는_경우_예외_발생_안함(){
    EnrolledUsers enrolledUsers = ofLeftFewSeats();

    assertThatCode(() -> enrolledUsers.add(NsUserTest.JAVAJIGI))
        .doesNotThrowAnyException();
  }

  @Test
  void 학생을_목록에_등록하면_학생등록목록_사이즈_1_증가(){
    EnrolledUsers enrolledUsers = ofNoUsersYet();

    enrolledUsers.add(NsUserTest.JAVAJIGI);

    assertThat(enrolledUsers.size()).isEqualTo(1);
  }



}