package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.Fixtures.aSession;
import static org.assertj.core.api.Assertions.assertThat;

class SessionJoinTest {

    @Test
    @DisplayName("수강 신청 승인")
    void test01() {
        SessionJoin sessionJoin = SessionJoin.apply(aSession().build(), NsUserTest.JAVAJIGI);

        sessionJoin.approve();

        assertThat(sessionJoin.getSessionJoinStatus()).isEqualTo(SessionJoinStatus.APPROVAL);
    }

    @Test
    @DisplayName("수강 신청 거절")
    void test02() {
        SessionJoin sessionJoin = SessionJoin.apply(aSession().build(), NsUserTest.JAVAJIGI);

        sessionJoin.reject();

        assertThat(sessionJoin.getSessionJoinStatus()).isEqualTo(SessionJoinStatus.REJECTION);
    }
}
