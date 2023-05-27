package nextstep.courses.domain;

import nextstep.courses.domain.type.CourseStatus;
import nextstep.courses.domain.type.CourseType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class SessionTest {
    @Test
    public void register_success() {
        // given
        Course course = new Course(1L,
                "Nextstep 강의",
                1L, LocalDateTime.now(),
                LocalDateTime.now(),
                CourseStatus.RECRUIT,
                CourseType.PAID);

        Session session = new Session(course, 1);

        // when
        session.register(new Student(1));

        // then
        assertThat(session.getStudentsSize()).isEqualTo(1);
    }

    @Test
    public void register_failed_by_duplication() {
        // given
        Course course = new Course(1L,
                "Nextstep 강의",
                1L, LocalDateTime.now(),
                LocalDateTime.now(),
                CourseStatus.RECRUIT,
                CourseType.PAID);

        Session session = new Session(course, 2);

        // when
        session.register(new Student(1));

        // then
        assertThatThrownBy(() -> session.register(new Student(1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 등록된 학생입니다.");
    }

    @Test
    public void register_failed_by_not_recuriting() {
        // given
        Course course = new Course(1L,
                "Nextstep 강의",
                1L, LocalDateTime.now(),
                LocalDateTime.now(),
                CourseStatus.READY,
                CourseType.PAID);
        Session session = new Session(course, 2);

        // then
        assertThatThrownBy(() -> session.register(new Student(1)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("아직 모집중인 강의가 아닙니다.");
    }

    @Test
    public void register_failed_by_capacity() {
        // given
        Course course = new Course(1L,
                "Nextstep 강의",
                1L, LocalDateTime.now(),
                LocalDateTime.now(),
                CourseStatus.RECRUIT,
                CourseType.PAID);
        Session session = new Session(course, 0);

        // then
        assertThatThrownBy(() -> session.register(new Student(1)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("더 이상 학생을 등록할 수 없습니다.");
    }
}