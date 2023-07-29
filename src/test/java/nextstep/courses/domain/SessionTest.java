package nextstep.courses.domain;

import nextstep.courses.NotChangeStatusException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    private Session session;

    @BeforeEach
    void setUp(){
        session = new Session(0L, "TDD 16기", 1, true);
    }

    @Test
    public void 수강신청_모집중_아님() throws Exception {
        // when, then
        assertThatThrownBy(()->session.enrolment(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalAccessException.class);
    }

    @Test
    public void 강의_모집시_준비중_상태_체크() throws Exception {
        // when
        session.startRecruiting();
        session.startSession();

        // then
        assertThatThrownBy(()->session.startRecruiting())
                .isInstanceOf(NotChangeStatusException.class);
    }

    @Test
    public void 강의_모집() throws Exception {
        // when
        SessionStatus sessionStatus = session.startRecruiting();

        assertThat(sessionStatus).isEqualTo(SessionStatus.RECRUITING);
    }

    @Test
    public void 수강신청_최대_인원_초과() throws Exception {
        // given
        session.startRecruiting();
        session.enrolment(NsUserTest.SANJIGI);

        // when, then
        assertThatThrownBy(()->session.enrolment(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalAccessException.class);
    }

    @Test
    public void 강의_시작시_모집중_상태_체크() throws Exception {
        // when, then
        assertThatThrownBy(()->session.startSession())
                .isInstanceOf(NotChangeStatusException.class);
    }

    @Test
    public void 강의_시작() throws Exception {
        // when
        session.startRecruiting();
        SessionStatus sessionStatus = session.startSession();

        assertThat(sessionStatus).isEqualTo(SessionStatus.PROCEEDING);
    }

    @Test
    public void 강의_종료시_진행중_상태_체크() throws Exception {
        // when, then
        assertThatThrownBy(()->session.endSession())
                .isInstanceOf(NotChangeStatusException.class);
    }

    @Test
    public void 강의_종료() throws Exception {
        // when
        session.startRecruiting();
        session.startSession();
        SessionStatus sessionStatus = session.endSession();

        // then
        assertThat(sessionStatus).isEqualTo(SessionStatus.END);
    }

    @Test
    public void 강의_아이디로_같은_강의_여부_확인() throws Exception {
        // when, then
        assertThat(session.isSessionWithId(0L)).isTrue();
        assertThat(session.isSessionWithId(1L)).isFalse();
    }
}
