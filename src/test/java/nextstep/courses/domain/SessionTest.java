package nextstep.courses.domain;

import nextstep.courses.domain.type.SessionStatus;
import nextstep.courses.domain.type.SessionType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class SessionTest {
    @Test
    public void register_success() {
        // given
        Session session = new Session(
                new Students(new HashSet<>()),
                2,
                SessionStatus.RECRUIT,
                SessionType.FREE,
                LocalDate.now(),
                LocalDate.now(),
                ""
        );

        // when
        session.register(1L);

        // then
        assertThat(session.getStudentsSize()).isEqualTo(1);
    }

    @Test
    public void register_failed_by_duplication() {
        // given
        Session session = new Session(
                new Students(new HashSet<>()),
                2,
                SessionStatus.RECRUIT,
                SessionType.FREE,
                LocalDate.now(),
                LocalDate.now(),
                ""
        );


        // when
        session.register(1L);

        // then
        assertThatThrownBy(() -> session.register(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 등록된 학생입니다.");
    }

    @Test
    public void register_failed_by_not_recuriting() {
        // given
        Session session = new Session(
                new Students(new HashSet<>()),
                2,
                SessionStatus.READY,
                SessionType.FREE,
                LocalDate.now(),
                LocalDate.now(),
                ""
        );

        // then
        assertThatThrownBy(() -> session.register(1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("아직 모집중인 강의가 아닙니다.");
    }

    @Test
    public void register_failed_by_capacity() {
        // given
        Session session = new Session(
                new Students(new HashSet<>()),
                0,
                SessionStatus.RECRUIT,
                SessionType.FREE,
                LocalDate.now(),
                LocalDate.now(),
                ""
        );

        // then
        assertThatThrownBy(() -> session.register(1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("더 이상 학생을 등록할 수 없습니다.");
    }
}