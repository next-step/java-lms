package nextstep.sessions.domain;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.UsageType;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SessionTest {

    @DisplayName("유료강의는 최대수강인원을 초과할 수 없다")
    @Test
    void validateUserLimitForPaidCourse() {
        List<NsUser> users = List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI);

        Assertions.assertThatThrownBy(() ->
                        new Session(users, UsageType.PAY, SessionStatus.RECRUITING, 1)
                                .validateUserLimitForPaidCourse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수강인원을 초과하였습니다.");
    }

}
