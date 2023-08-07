package nextstep.courses.domain;

import nextstep.courses.NotChangeStatusException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionManagementTest {
    SessionManagement sessionManagement;

    @BeforeEach
    void setUp(){
        sessionManagement = new SessionManagement(true, 1);
    }

    @Test
    public void 수강신청_모집중_아님() throws Exception {
        // when, then
        assertThatThrownBy(()-> sessionManagement.enrolment(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 강의_모집시_준비중_상태_체크() throws Exception {
        // when
        sessionManagement.startRecruiting();
        sessionManagement.startSession();

        // then
        assertThatThrownBy(()-> sessionManagement.startRecruiting())
                .isInstanceOf(NotChangeStatusException.class);
    }

    @Test
    public void 강의_모집() throws Exception {
        // when
        SessionStatus sessionStatus = sessionManagement.startRecruiting();

        assertThat(sessionStatus).isEqualTo(SessionStatus.RECRUITING);
    }

    @Test
    public void 수강신청_최대_인원_초과() throws Exception {
        // given
        sessionManagement.startRecruiting();
        sessionManagement.enrolment(NsUserTest.SANJIGI);

        // when, then
        assertThatThrownBy(()-> sessionManagement.enrolment(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 강의_시작시_모집중_상태_체크() throws Exception {
        // when, then
        assertThatThrownBy(()-> sessionManagement.startSession())
                .isInstanceOf(NotChangeStatusException.class);
    }

    @Test
    public void 강의_시작() throws Exception {
        // when
        sessionManagement.startRecruiting();
        SessionStatus sessionStatus = sessionManagement.startSession();

        assertThat(sessionStatus).isEqualTo(SessionStatus.PROCEEDING);
    }

    @Test
    public void 강의_종료시_진행중_상태_체크() throws Exception {
        // when, then
        assertThatThrownBy(()-> sessionManagement.endSession())
                .isInstanceOf(NotChangeStatusException.class);
    }

    @Test
    public void 강의_종료() throws Exception {
        // when
        sessionManagement.startRecruiting();
        sessionManagement.startSession();
        SessionStatus sessionStatus = sessionManagement.endSession();

        // then
        assertThat(sessionStatus).isEqualTo(SessionStatus.END);
    }
}
