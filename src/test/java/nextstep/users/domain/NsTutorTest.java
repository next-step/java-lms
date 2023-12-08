package nextstep.users.domain;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionUserStatus;
import nextstep.tutor.domain.NsTutor;
import nextstep.tutor.exception.SessionApproveException;
import nextstep.tutor.exception.SessionCancelException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.users.domain.NsUserTest.HONUX;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.*;

class NsTutorTest {
    public static final NsTutor KWC = new NsTutor(1L, "kwc", "password", "name", "kwc@slipp.net");
    public static final NsTutor TESTTRACE = new NsTutor(2L, "testtrace", "password", "name", "testtrace@slipp.net");

    @Test
    @DisplayName("실패 - 강사는 본인 강의가 아닐 경우 수강 승인을 할 수 없다.")
    void fail_session_approve_not_equal_tutor() {
        Session session = new Session(1L);

        assertThatThrownBy(() -> TESTTRACE.approve(session, HONUX))
                .isInstanceOf(SessionApproveException.class)
                .hasMessage("본인 담당 강의만 수강 승인 할 수 있습니다.");
    }

    @Test
    @DisplayName("실패 - 강사는 선발 되지 않은 유저는 수강 승인 할 수 없다.")
    void fail_session_approve_not_selection_user() {
        Session session = new Session(1L);

        assertThatThrownBy(() -> KWC.approve(session, HONUX))
                .isInstanceOf(SessionApproveException.class)
                .hasMessage("선발 되지 않은 유저는 수강 승인 할 수 없습니다.");
    }

    @Test
    @DisplayName("성공 - 강사는 선발된 유저를 수강 승인 할 수 있다.")
    void success_session_approve_selection_user() {
        Session session = new Session(1L);
        assertThat(KWC.approve(session, JAVAJIGI)).isEqualTo(SessionUserStatus.APPROVE);
    }

    @Test
    @DisplayName("실패 - 강사는 본인 강의가 아닐 경우 수강 취소를 할 수 없다.")
    void fail_session_cancel_not_equal_tutor() {
        Session session = new Session(1L);

        assertThatThrownBy(() -> TESTTRACE.cancel(session))
                .isInstanceOf(SessionCancelException.class)
                .hasMessage("본인 담당 강의만 수강 취소 할 수 있습니다.");
    }

    @Test
    @DisplayName("성공 - 강사는 수강 신청한 유저를 수강 취소 할 수 있다.")
    void success_session_cancel_selection_user() {
        Session session = new Session(1L);
        assertThat(KWC.cancel(session)).isEqualTo(SessionUserStatus.CANCEL);
    }

}
