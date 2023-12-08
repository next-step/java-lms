package nextstep.courses.domain;

import nextstep.courses.exception.SessionUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static nextstep.courses.domain.SelectionStatus.SELECTED;
import static nextstep.courses.domain.SessionUserStatus.APPROVE;
import static nextstep.courses.domain.SessionUserStatus.CANCEL;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.*;

class SessionUsersTest {

    public SessionUsers selectedSessionUser() {
        return new SessionUsers(
                Set.of(new SessionUser(1L, JAVAJIGI.getId(), SELECTED))
        );
    }

    @Test
    @DisplayName("성공 - 유저가 강의 신청을 처음 할 경우 예외가 발생 하지 않는다.")
    void success_session_user_add() {
        SessionUsers sessionUsers = new SessionUsers();
        assertThatCode(() -> sessionUsers.addUser(1L, SELECTED, JAVAJIGI))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 강의를 이미 신청한 유저는 중복으로 신청할 수 없다.")
    void fail_session_user_add_duplicate() {
        SessionUsers sessionUsers = selectedSessionUser();

        assertThatThrownBy(() -> sessionUsers.addUser(1L, SELECTED, JAVAJIGI))
                .isInstanceOf(SessionUserException.class)
                .hasMessage("강의를 이미 신청한 유저이므로 중복으로 신청할 수 없습니다.");
    }

    @Test
    @DisplayName("성공 - 강의 승인 후 승인된 수강 신청한 유저를 반환 한다")
    void success_approve_session_user() {
        SessionUsers sessionUsers = selectedSessionUser();
        SessionUser sessionUser = sessionUsers.approve(JAVAJIGI);

        assertThat(sessionUser.sessionUserStatus()).isEqualTo(APPROVE);
    }

    @Test
    @DisplayName("실패 - 강의 신청을 하지 않은 유저는 승인 처리 할 수 없다.")
    void fail_approve_session_user() {
        SessionUsers sessionUsers = selectedSessionUser();

        assertThatThrownBy(() -> sessionUsers.approve(SANJIGI))
                .isInstanceOf(SessionUserException.class)
                .hasMessage("강의를 신청 하지 않은 유저입니다.");
    }

    @Test
    @DisplayName("성공 - 강의 승인 후 취소된 수강 신청한 유저를 반환 한다")
    void success_cancel_session_user() {
        SessionUsers sessionUsers = selectedSessionUser();
        SessionUser sessionUser = sessionUsers.cancel(JAVAJIGI);

        assertThat(sessionUser.sessionUserStatus()).isEqualTo(CANCEL);
    }

    @Test
    @DisplayName("실패 - 강의 신청을 하지 않은 유저는 취소 처리 할 수 없다.")
    void fail_cancel_session_user() {
        SessionUsers sessionUsers = selectedSessionUser();

        assertThatThrownBy(() -> sessionUsers.cancel(SANJIGI))
                .isInstanceOf(SessionUserException.class)
                .hasMessage("강의를 신청 하지 않은 유저입니다.");
    }

}
