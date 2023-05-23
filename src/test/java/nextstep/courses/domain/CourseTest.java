package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class CourseTest {

  @Test
  void Cours과정_생성_후_Session_등록_테스트() {
    Course course = new Course(
        1L,
        "title",
        1L,
        LocalDateTime.now(),
        LocalDateTime.now()
    );

    course.addSession(SessionTest.PREPARING_SESSION_NO_USERS_YET);

    assertThat(course.getSessionsSize()).isEqualTo(1);
  }

}