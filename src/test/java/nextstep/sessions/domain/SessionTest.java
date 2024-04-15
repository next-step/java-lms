package nextstep.sessions.domain;

import static nextstep.sessions.domain.SessionProgressStatus.PREPARING;
import static nextstep.sessions.domain.SessionRecruitingStatus.RECRUITING;
import static nextstep.users.domain.NsUserType.WOOTECO;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.Course;
import nextstep.enrollment.domain.Enrollment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

class SessionTest {

    private NsUser user;

    @BeforeEach
    void setUp() {
        user = new NsUser(3L, "lee", "password", "name", "lee@slipp.net", WOOTECO);
    }

    @Test
    void 수강_인원이_가득_찼으면_true를_반환한다() {
        // given
        final Course course = new Course("TDD, 클린 코드 with Java", 1L);
        final Session session = new Session(1, RECRUITING, PREPARING, 100000L, LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2), course);
        final Enrollment enrollment = new Enrollment(session, user);
        enrollment.enroll(100000L);
        enrollment.approveBy(NsUserTest.JAVAJIGI);

        // when
        final boolean isFull = session.isFull();

        // then
        assertThat(isFull).isTrue();
    }

    @Test
    void 수강_인원이_가득_차지_않았으면_false를_반환한다() {
        // given
        final Course course = new Course("TDD, 클린 코드 with Java", 1L);
        final Session session = new Session(4, RECRUITING, PREPARING, 100000L, LocalDateTime.now().plusDays(1),
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
        final Session session = new Session(4, RECRUITING, PREPARING, 100000L, LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2), course);
        final Enrollment enrollment = new Enrollment(session, user);
        enrollment.enroll(100000L);
        enrollment.approveBy(NsUserTest.JAVAJIGI);

        // when
        final boolean canNotEnroll = session.canEnroll(enrollment);

        // then
        assertThat(canNotEnroll).isTrue();
    }

    @Test
    void 등록하지_않은_경우_false를_반환한다() {
        // given
        final Course course = new Course("TDD, 클린 코드 with Java", 1L);
        final Session session = new Session(4, RECRUITING, PREPARING, 100000L, LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2), course);
        final Enrollment enrollment = new Enrollment(session, NsUserTest.JAVAJIGI);

        // when
        final boolean canEnroll = session.canEnroll(enrollment);

        // then
        assertThat(canEnroll).isFalse();
    }
}
