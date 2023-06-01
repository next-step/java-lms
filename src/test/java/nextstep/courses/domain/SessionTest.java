package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nextstep.courses.SessionFixture.강의_과정_1;
import static nextstep.courses.SessionFixture.강의_과정_3;

class SessionTest {
    private Session session;

    @BeforeEach
    public void before() {
        session = 강의_과정_1();
    }


    @Test
    void 수강신청_모집_비모집_상태가_없는경우() {
        Session session = 강의_과정_3();
        Assertions.assertThat(session.getSessionEnrollmentStatus()).isEqualTo(SessionEnrollmentStatus.NO_INFO);
    }

    @Test
    void 수강신청_테스트() {
        session.enrollSession(NsUserTest.JAVAJIGI);
        Assertions.assertThat(session.getUserCount()).isEqualTo(1);
    }

    @Test
    void 강의_상태가_진행중이_아닐때_예외_throw() {
        session.changeSessionStatus(SessionStatus.READY);
        Assertions.assertThatThrownBy(() -> session.enrollSession(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 최대_수강_인원을_초과시_예외_throw() {
        session.enrollSession(NsUserTest.JAVAJIGI);
        Assertions.assertThatThrownBy(() -> session.enrollSession(NsUserTest.SANJIGI)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 강의_상태가_진행중이고_모집중이_아닐때_예외_throw() {
        session.changeSessionStatus(SessionStatus.OPEN);
        session.nonEnroll();
        Assertions.assertThatThrownBy(() -> session.enrollSession(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }
}
