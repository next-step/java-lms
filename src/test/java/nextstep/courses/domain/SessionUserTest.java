package nextstep.courses.domain;

import nextstep.courses.exception.SessionUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionUserStatus.APPROVE;
import static nextstep.courses.domain.SessionUserStatus.CANCEL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionUserTest {

    SessionUser SELECTED_USER = new SessionUser(1L, 1L, SelectionStatus.SELECTED);
    SessionUser NOT_SELECTED_USER = new SessionUser(1L, 2L, SelectionStatus.NOT_SELECTED);

    @Test
    @DisplayName("성공 - 강의 승인 처리된 유저를 반환한다.")
    void success_approve_session_user() {
        SessionUser approve = SELECTED_USER.approve();
        assertThat(approve.sessionUserStatus()).isEqualTo(APPROVE);
    }

    @Test
    @DisplayName("실패 - 강의 승인 처리된 유저를 반환한다.")
    void fail_approve_session_user() {
        assertThatThrownBy(() -> NOT_SELECTED_USER.approve())
                .isInstanceOf(SessionUserException.class)
                .hasMessage("선발 되지 않은 유저는 수강 승인 할 수 없습니다.");
    }

    @Test
    @DisplayName("성공 - 강의 취소 처리된 유저를 반환 한다.")
    void success_cancel_session_user() {
        SessionUser approve = SELECTED_USER.cancel();
        assertThat(approve.sessionUserStatus()).isEqualTo(CANCEL);
    }

}
