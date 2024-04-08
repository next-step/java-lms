package nextstep.courses.domain.course;

import nextstep.courses.domain.session.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

  private static Course COURSE;

  @Test
  @DisplayName("정상 과정 상세 정보, 기수, 강의 정보를 입력하는 경우" +
      "COURSE 생성 테스트")
  void course_create_test() {

    settingForCourseTest();

    assertThat(COURSE.getTitle()).isEqualTo("TDD, 클린 코드 with Java");
    assertThat(COURSE.getCreatorId()).isEqualTo(100L);
  }

  private static void settingForCourseTest() {
    COURSE = new Course(1L, new Generation(1L), "TDD, 클린 코드 with Java", 100L, LocalDateTime.now(), LocalDateTime.now());

    Session session1 = new Session(
        1L,
        new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1L)),
        new SessionInfo("자동차 경주", SessionStatus.IN_PROGRESS, true, 0L, null),
        new CoverImage("자동차 경주 이미지 커버", ImageType.PNG, new CoverImageMeta(1024, 300, 200))
    );

    Session session2 = new Session(
        2L,
        new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1L)),
        new SessionInfo("사다리 타기", SessionStatus.IN_PROGRESS, true, 0L, null),
        new CoverImage("사다리 타기 이미지 커버", ImageType.PNG, new CoverImageMeta(1024, 300, 200))
    );

    Session session3 = new Session(
        3L,
        new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1L)),
        new SessionInfo("수강신청", SessionStatus.IN_PROGRESS, false, 50000L, 100),
        new CoverImage("수강신청 이미지 커버", ImageType.PNG, new CoverImageMeta(1024, 300, 200))
    );

    COURSE.addSessions(Set.of(session1, session2, session3));
  }
}
