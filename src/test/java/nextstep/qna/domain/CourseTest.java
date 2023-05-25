package nextstep.qna.domain;

import nextstep.courses.domain.Course;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CourseTest {

  public static final Course C1 = new Course("TDD, 클린 코드 with Java", NsUserTest.JAVAJIGI.getId());

  @Test
  @DisplayName("과정(Course)은 기수 단위로 여러 개의 강의(Session)를 가질 수 있다.")
  void test() {

  }
}
