package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import nextstep.users.domain.NsUserTest;

class EnrolledUsersTest {

  public static final EnrolledUsers TEST_ENROLLED_NO_USERS = new EnrolledUsers(new MaxEnrollment(10));
  public static final EnrolledUsers TEST_ENROLLED_USERS = new EnrolledUsers(new MaxEnrollment(10)){
    {
      add(NsUserTest.JAVAJIGI);
      add(NsUserTest.SANJIGI);
    }
  };



}