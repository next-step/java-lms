package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.SessionFixture.강의_과정_1;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;

class SessionUsersTest {
    @Test
    void 최대_수강_인원을_초과할_수_없다() {
        int maxEnrollment = 1;
        SessionUser sessionUser = new SessionUser(강의_과정_1(), ApprovalStatus.APPROVAL, JAVAJIGI, LocalDateTime.now(), LocalDateTime.now());
        SessionUsers sessionUsers = new SessionUsers(maxEnrollment);
        sessionUsers.addEnroll(sessionUser);
        Assertions.assertThatThrownBy(() -> sessionUsers.addEnroll(sessionUser)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 수강_신청_승인_변경() {
        SessionUser sessionUser = new SessionUser(강의_과정_1(), ApprovalStatus.REQUEST, JAVAJIGI, LocalDateTime.now(), LocalDateTime.now());
        sessionUser.approve();
        assertThat(sessionUser.getApprovalStatus()).isEqualTo(ApprovalStatus.APPROVAL);
    }

    @Test
    void 수강_신청_거절_변경() {
        SessionUser sessionUser = new SessionUser(강의_과정_1(), ApprovalStatus.REQUEST, JAVAJIGI, LocalDateTime.now(), LocalDateTime.now());
        sessionUser.reject();
        assertThat(sessionUser.getApprovalStatus()).isEqualTo(ApprovalStatus.REJECTION);
    }
}
