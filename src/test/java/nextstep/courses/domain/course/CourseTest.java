package nextstep.courses.domain.course;

import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class CourseTest {

    @DisplayName("입력받은 Period 시작일이 코스의 생성일보다 빠르면 에러를 발생시킵니다.")
    @Test
    void afterCreated() {
        // given
        Period period = new Period(LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));
        Course course = new Course(1L, "title", 1L, LocalDateTime.now().plusMinutes(10), LocalDateTime.now().plusMinutes(10));
        Session session = new Session(null, period, SessionType.freeSession());
        // when
        // then
        Assertions.assertThatThrownBy(() -> course.addSession(session))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("세션 시작일이 코스 생성일보다 빠를 수 없습니다.");
    }
}