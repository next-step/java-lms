package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import nextstep.courses.domain.Course;
import nextstep.enrollment.domain.Enrollment;
import nextstep.users.domain.NsUserTest;

class SessionTest {

    @Test
    void 수강_인원이_가득_찼으면_true를_반환한다() {
        // given
        final Course course = new Course("TDD, 클린 코드 with Java", 1L);
        final Session session = new Session(1, SessionRecruitingStatus.RECRUITING, 100000L, LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2), course);
        new Enrollment(session, NsUserTest.JAVAJIGI).enroll(100000L);

        // when
        final boolean isFull = session.isFull();

        // then
        assertThat(isFull).isTrue();
    }

    @Test
    void 수강_인원이_가득_차지_않았으면_false를_반환한다() {
        // given
        final Course course = new Course("TDD, 클린 코드 with Java", 1L);
        final Session session = new Session(4, SessionRecruitingStatus.RECRUITING, 100000L, LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2), course);

        // when
        final boolean isNotFull = session.isFull();

        // then
        assertThat(isNotFull).isFalse();
    }

    @Test
    void 이미_등록한_경우_true를_반환한다() {
        // given
        final Course course = new Course("TDD, 클린 코드 with Java", 1L);
        final Session session = new Session(4, SessionRecruitingStatus.RECRUITING, 100000L, LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2), course);
        final Enrollment enrollment = new Enrollment(session, NsUserTest.JAVAJIGI);
        enrollment.enroll(100000L);

        // when
        final boolean canNotEnroll = session.canEnroll(enrollment);

        // then
        assertThat(canNotEnroll).isTrue();
    }

    @Test
    void 등록하지_않은_경우_false를_반환한다() {
        // given
        final Course course = new Course("TDD, 클린 코드 with Java", 1L);
        final Session session = new Session(4, SessionRecruitingStatus.RECRUITING, 100000L, LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2), course);
        final Enrollment enrollment = new Enrollment(session, NsUserTest.JAVAJIGI);

        // when
        final boolean canEnroll = session.canEnroll(enrollment);

        // then
        assertThat(canEnroll).isFalse();
    }
}
