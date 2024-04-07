package nextstep.courses.domain.course;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static nextstep.courses.domain.course.CourseDetailInfo.COURSE_TITLE_IS_INCORRECT;
import static nextstep.courses.domain.course.CourseDetailInfo.NEGATIVE_NUMBER_OR_ZERO_IS_NOT_ALLOWED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CourseDetailInfoTest {
  @Test
  @DisplayName("정상 생성자 id와 타이틀 명을 넣은 경우" +
      "CourseDetailInfo 생성 테스트")
  void courseDetailInfoTest() {
    Long givenCreatorId = 1L;
    String givenTitleName = "테스트 과정 명";

    CourseDetailInfo courseDetailInfo = new CourseDetailInfo(givenCreatorId, givenTitleName);
    assertThat(courseDetailInfo.getCreatorId()).isEqualTo(givenCreatorId);
    assertThat(courseDetailInfo.getTitleName()).isEqualTo(givenTitleName);
  }

  @Test
  @DisplayName("생성자 id를 음수로 넣은 경우" +
      "exception 테스트")
  void courseDetailInfoTest2() {
    Long given = -1000L;
    assertThatThrownBy(() -> new CourseDetailInfo(given, "테스트 과정 명"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(NEGATIVE_NUMBER_OR_ZERO_IS_NOT_ALLOWED, given));
  }

  @Test
  @DisplayName("생성자 id를 0으로 넣은 경우" +
      "exception 테스트")
  void courseDetailInfoTest3() {
    Long given = 0L;
    assertThatThrownBy(() -> new CourseDetailInfo(given, "테스트 과정 명"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(NEGATIVE_NUMBER_OR_ZERO_IS_NOT_ALLOWED, given));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @DisplayName("과정 명이 null 또는 empty 인 경우" +
      "exception 테스트")
  void courseDetailInfoTest4(String given) {
    assertThatThrownBy(() -> new CourseDetailInfo(100L, given))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(COURSE_TITLE_IS_INCORRECT, given));
  }
}
