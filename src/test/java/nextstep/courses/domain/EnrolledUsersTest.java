package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnrolledUsersTest {

  public static EnrolledUsers TEST_ENROLLED_NO_USERS_YET = new EnrolledUsers(new MaxEnrollment(10));
  public static EnrolledUsers TEST_ENROLLED_USERS_LEFT_FEW_SEATS = new EnrolledUsers(new MaxEnrollment(10)){
    {
      add(NsUserTest.JAVAJIGI);
      add(NsUserTest.SANJIGI);
    }
  };

  public static EnrolledUsers TEST_ENROLLED_FULL_USERS = new EnrolledUsers(new MaxEnrollment(2)){
    {
      add(NsUserTest.JAVAJIGI);
      add(NsUserTest.SANJIGI);
    }
  };

  public static EnrolledUsers TEST_ENROLLED_LEFT_ONE_SEAT_USERS = new EnrolledUsers(new MaxEnrollment(2)){
    {
      add(NsUserTest.JAVAJIGI);
    }
  };

 @AfterEach
  void setUp() {
   TEST_ENROLLED_NO_USERS_YET = new EnrolledUsers(new MaxEnrollment(10));
   TEST_ENROLLED_USERS_LEFT_FEW_SEATS = new EnrolledUsers(new MaxEnrollment(10)){
     {
       add(NsUserTest.JAVAJIGI);
       add(NsUserTest.SANJIGI);
     }
   };

   TEST_ENROLLED_FULL_USERS = new EnrolledUsers(new MaxEnrollment(2)){
     {
       add(NsUserTest.JAVAJIGI);
       add(NsUserTest.SANJIGI);
     }
   };

    TEST_ENROLLED_LEFT_ONE_SEAT_USERS = new EnrolledUsers(new MaxEnrollment(2)){
      {
        add(NsUserTest.JAVAJIGI);
      }
    };
  }

  @Test
  void 수강_인원이_비어있으면_isEmpty_true_반환_테스트() {
    EnrolledUsers enrolledUsers = TEST_ENROLLED_NO_USERS_YET;

    boolean isEmpty = enrolledUsers.isEmpty();

    assertThat(isEmpty).isTrue();
  }

  @Test
  void 수강_인원이_가득차있으면_isEmpty_false_반환_테스트() {
    EnrolledUsers enrolledUsers = TEST_ENROLLED_FULL_USERS;

    boolean isEmpty = enrolledUsers.isEmpty();

    assertThat(isEmpty).isFalse();
  }

  @Test
  void 수강_인원이_가득차_있는_상태에서_수강생을_추가하는_경우_예외_발생(){
    EnrolledUsers enrolledUsers = TEST_ENROLLED_FULL_USERS;

    assertThatThrownBy(() -> enrolledUsers.add(NsUserTest.JAVAJIGI))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("최대 인원수를 넘어서 수강 신청할 수 없습니다.");
  }

  @Test
  void 수강_인원이_가득차지_않은_상태에서_수강생을_추가하는_경우_예외_발생_안함(){
    EnrolledUsers enrolledUsers = TEST_ENROLLED_LEFT_ONE_SEAT_USERS;

    assertThatCode(() -> enrolledUsers.add(NsUserTest.JAVAJIGI))
        .doesNotThrowAnyException();
  }

  @Test
  void 학생을_목록에_등록하면_학생등록목록_사이즈_1_증가(){
    EnrolledUsers enrolledUsers = TEST_ENROLLED_NO_USERS_YET;

    enrolledUsers.add(NsUserTest.JAVAJIGI);

    assertThat(enrolledUsers.size()).isEqualTo(1);
  }



}