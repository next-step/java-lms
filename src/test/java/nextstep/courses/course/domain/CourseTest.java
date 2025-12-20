package nextstep.courses.course.domain;

import static nextstep.courses.cohort.domain.fixture.CohortFixture.식별자와_상태를_전달받아_기수픽스처를_생성한다;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.courses.cohort.domain.Cohorts;
import nextstep.courses.cohort.domain.enumeration.CohortStateType;
import nextstep.courses.course.domain.enumaration.CourseChargeType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class CourseTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void 강의제목이_없이_강의를_생성하면_예외처리_할_수_있다(String title) {
        assertThatThrownBy(
                () -> new Course(title, 1L)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(longs = {0L})
    void 강의생성자_정보없이_강의를_생성하면_예외처리_할_수_있다(Long creatorId) {
        assertThatThrownBy(
                () -> new Course("TDD, 객체지향 과정", creatorId)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 강의_결제타입_없이_강의를_생성하면_예외처리_할_수_있다() {
        assertThatThrownBy(
                () -> new Course("TDD, 객체지향 과정", 1L, null)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 유료강의인지_확인할_수_있다() {
        Course course = new Course("TDD, 객체지향 과정", 1L, CourseChargeType.PAID);

        assertThat(
                course.isPaid()
        ).isTrue();
    }

    @Test
    void 무료강의인지_확인할_수_있다() {
        Course course = new Course("TDD, 객체지향 과정", 1L, CourseChargeType.FREE);

        assertThat(
                course.isFree()
        ).isTrue();
    }

}