package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    @Test
    void 수강신청_성공() throws Exception {
        NsUser loginUser = NsUsers.createNsUser(4L);
        Session dut = Sessions.createSessionWithEnrollment(1L, 50, SessionStatus.ENROLLING);
        dut.enroll(loginUser);
    }

    @Test
    void 수강신청_실패_이미_수강신청한_회원() {
        NsUser loginUser = NsUsers.createNsUser(4L);
        Session dut = Sessions.createSessionWithEnrollment(1L, 50, SessionStatus.ENROLLING);
        assertThatThrownBy(() -> {
            dut.enroll(loginUser);
            dut.enroll(loginUser);
        }).isInstanceOf(AlreadyEnrollmentException.class);
    }
}
