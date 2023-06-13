package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.fixtures.SessionFixtures.*;
import static org.assertj.core.api.Assertions.*;

public class SessionJoinTest {

    @DisplayName("수강 신청 승인")
    @Test
    void test01() {
        SessionJoin sessionJoin = SessionJoin.apply(testSession1(), NsUserTest.JAVAJIGI);

        sessionJoin.approve();

        assertThat(sessionJoin.getSessionJoinStatus()).isEqualTo(SessionJoinStatus.APPROVAL);
    }

    @DisplayName("수강 신청 거절")
    @Test
    void test02() {
        SessionJoin sessionJoin = SessionJoin.apply(testSession1(), NsUserTest.JAVAJIGI);

        sessionJoin.reject();

        assertThat(sessionJoin.getSessionJoinStatus()).isEqualTo(SessionJoinStatus.REJECTION);
    }

}
