package nextstep.courses.domain;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CourseTest {

  @Test
  @DisplayName("코스 생성 테스트")
  public void writingMethodName() throws Exception {
    // given
    Course course = new Course(1L, "test", 1L, LocalDateTime.of(2023, 11, 23, 15, 30, 00),
        LocalDateTime.of(2023, 11, 30, 15, 30, 00));
    CoverImage coverImage = CoverImage.defaultOf("test.png", 1000L, 900, 600);
    Lecture freeLecture = Lecture.freeOf("free lecture", coverImage, LectureType.FREE,
        LocalDateTime.of(2023, 11, 23, 15, 30, 00),
        LocalDateTime.of(2023, 11, 23, 15, 30, 00));

    // when
    course.addLecture(freeLecture);
    int result = course.numberOfLecture();

    // then
    Assertions.assertThat(result).isEqualTo(1);
  }
}
