package nextstep.courses.domain;

import nextstep.courses.data.CourseMaker;
import nextstep.courses.data.SessionMaker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CourseTest {

    private static final Course COURSE = CourseMaker.makeCourse();

    @DisplayName("강의시작일자가 Null일경우 에러를 던진다.")
    @Test
    void 강의시작일자_Null_에러() {
        assertThatThrownBy(() -> {
            COURSE.openSession(0L, null, now(), "imageUrl", true, 10);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("강의종료일자가 Null일경우 에러를 던진다.")
    @Test
    void 강의종료일자_Null_에러() {
        assertThatThrownBy(() -> {
            COURSE.openSession(0L, now(), null, "imageUrl", true, 10);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("강의모집인원이 음수일경우 에러를 던진다.")
    @Test
    void 강의_모집인원_음수_에러() {
        assertThatThrownBy(() -> {
            COURSE.openSession(0L, now(), now(), "imageUrl", true, -1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("강의 생성시 리턴되는 강의정보 결과 검증")
    @Test
    void 강의_생성결과_검증() {
        assertThat(COURSE.openSession(0L, now(), now(), "imageUrl", true, 10))
                .isEqualTo(SessionMaker.makeSession(0L, COURSE));
    }

}