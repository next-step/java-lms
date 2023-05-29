package nextstep.courses.domain;

import static nextstep.courses.domain.SessionTest.ofPreparingSessionNoUsersYet;
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
        "1기",
        LocalDateTime.now(),
        LocalDateTime.now()
    );

    course.addSession(ofPreparingSessionNoUsersYet());

    assertThat(course.getSessionsSize()).isEqualTo(1);
  }

}