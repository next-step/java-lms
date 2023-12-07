package nextstep.courses.domain.session;

import nextstep.courses.domain.course.Course;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class PeriodTest {

    @DisplayName("코스의 생성일보다 세션 시작날짜가 이전이면 false를 반환합니다.")
    @Test
    void preSession() {
        // given
        Period period = createPeriod(LocalDateTime.of(2023, 11, 1, 14, 0), LocalDateTime.of(2023, 11, 20, 0, 0));
        Course course = new Course(1L, "course", 1L,
                LocalDateTime.of(2023, 12, 1, 0, 0),
                LocalDateTime.of(2023, 12, 1, 0, 0));
        // when
        boolean result = period.isAfterCourseCreatedDate(course);
        // then
        Assertions.assertThat(result).isFalse();
    }

    @DisplayName("코스의 생성일보다 세션 시작날짜가 이후이면 true를 반환합니다.")
    @Test
    void afterSession() {
        // given
        Period period = createPeriod(LocalDateTime.of(2023, 11, 1, 14, 0),
                LocalDateTime.of(2023, 11, 20, 0, 0));
        Course course = new Course(1L, "course", 1L,
                LocalDateTime.of(2023, 10, 1, 0, 0),
                LocalDateTime.of(2023, 12, 1, 0, 0));
        // when
        boolean result = period.isAfterCourseCreatedDate(course);
        // then
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("입력받은 세션의 시작날짜가 종료날짜보다 이전이면 true를 반환합니다.")
    @Test
    void preEnd() {
        // given
        Period period = createPeriod(LocalDateTime.of(2023, 11, 1, 14, 0),
                LocalDateTime.of(2023, 11, 20, 0, 0));
        Course course = new Course(1L, "course", 1L,
                LocalDateTime.of(2023, 10, 1, 0, 0),
                LocalDateTime.of(2023, 12, 1, 0, 0));
        // when
        boolean result = period.isAfterCourseCreatedDate(course);
        // then
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("입력받은 세션의 시작날짜가 종료날짜보다 이후면 false를 반환합니다.")
    @Test
    void afterEnd() {
        // given
        Period period = createPeriod(LocalDateTime.of(2023, 11, 1, 14, 0),
                LocalDateTime.of(2023, 10, 20, 0, 0));
        Course course = new Course(1L, "course", 1L,
                LocalDateTime.of(2023, 10, 1, 0, 0),
                LocalDateTime.of(2023, 12, 1, 0, 0));
        // when
        boolean result = period.isAfterCourseCreatedDate(course);
        // then
        Assertions.assertThat(result).isFalse();
    }

    private Period createPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return new Period(startDateTime, endDateTime);
    }
}