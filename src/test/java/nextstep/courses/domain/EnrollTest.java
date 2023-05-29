package nextstep.courses.domain;

import nextstep.fixture.TestFixture;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EnrollTest {

    @BeforeEach
    public void setUp() {
        TestFixture.fixtureInit();
    }

    @DisplayName("Enroll 과 연관있는 Session 인지 확인한다")
    @Test
    public void enroll() {
        //given
        Session session = TestFixture.LIME_SESSION;
        session.setSessionId(new SessionId(22L));
        session.toRecruitingState();
        Session otherSession = TestFixture.LEMON_SESSION;
        otherSession.setSessionId(new SessionId(33L));
        otherSession.toRecruitingState();
        NsUser user = TestFixture.BADAJIGI;
        //when
        Enroll enroll = session.register(user.getUserCode(), 0);
        //then
        assertThat(enroll.isEnrolledSession(session.getSessionId()))
                .as("관계있는 세션에 대하여 참을 반환한다")
                .isTrue();
        assertThat(enroll.isEnrolledSession(otherSession.getSessionId()))
                .as("관계없는 세선에 대하여 거짓을 반환한다")
                .isFalse();
    }
}