package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class SessionValidatorTest {
    @Test
    void createTest_수강신청_중복요청() {
        SessionValidator sessionHeadCount = new SessionValidator(SessionState.PREPARING, SessionRecruitState.RECRUITING);
        sessionHeadCount.addPerson(NsUserTest.JAVAJIGI);
        sessionHeadCount.addPerson(NsUserTest.SANJIGI);

        assertThatThrownBy(() -> sessionHeadCount.addPerson(NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복 신청입니다.");
    }

    @Test
    void createTest_수강신청_모집중이아님() {
        SessionValidator sessionHeadCount = new SessionValidator(SessionState.CLOSE);

        assertThatThrownBy(() -> sessionHeadCount.addPerson(NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 강의는 수강신청중이 아닙니다.");
    }
}