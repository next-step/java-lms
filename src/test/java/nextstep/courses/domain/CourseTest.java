package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.courses.domain.lectures.FreeLecture;
import nextstep.courses.domain.lectures.Lecture;
import nextstep.courses.domain.lectures.LectureStatus;
import nextstep.courses.domain.lectures.RegistrationPeriod;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CourseTest {

  @Test
  @DisplayName("코스 생성 테스트")
  public void writingMethodName() throws Exception {
    // given
    LocalDateTime startDate = LocalDateTime.of(2023, 4, 3, 11, 30);
    LocalDateTime endDate = LocalDateTime.of(2023, 6, 3, 11, 30);
    CoverImage coverImage = CoverImage.defaultOf("file.png", 2000L, 900, 600);
    Course course = new Course(1L, "test", 1L, LocalDateTime.of(2023, 11, 23, 15, 30, 00),
        LocalDateTime.of(2023, 11, 30, 15, 30, 00));
    Lecture lecture = new FreeLecture(0L, "test", coverImage, LectureStatus.PREPARING,
        new RegistrationPeriod(startDate, endDate));

    // when
    course.addLecture(lecture);
    int result = course.numberOfLecture();

    // then
    Assertions.assertThat(result).isEqualTo(1);
  }
}
