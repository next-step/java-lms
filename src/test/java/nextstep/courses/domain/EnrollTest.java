package nextstep.courses.domain;

import nextstep.fixture.TestFixture;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class EnrollTest {

    @BeforeEach
    public void setUp() {
        TestFixture.fixtureInit();
    }

    @DisplayName("Enroll 과 연관있는 Session 인지 검증한다")
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
        assertAll("Session 에 Enroll 등록하기 기능을 검증한다",
                () -> assertThat(enroll.isEnrolledSession(session.getSessionId()))
                .as("세션 등록 여부를 검증한다")
                .isTrue(),
                () -> assertThat(enroll.isEnrolledSession(otherSession.getSessionId()))
                .as("관계없는 세선에 대하여 거짓을 반환한다")
                .isFalse()
        );
    }

    @DisplayName("수강 승인 기능을 검증한다")
    @Test
    public void approved() {
        //given
        Session session = TestFixture.LIME_SESSION;
        session.setSessionId(new SessionId(22L));
        session.toRecruitingState();
        NsUser user = TestFixture.BADAJIGI;
        //when
        Enroll enroll = session.register(user.getUserCode(), 0);
        enroll.approve();
        //then
        assertAll("수강신청 후 (운영진 혹은 강사가)승인 기능을 검증한다",
                () -> assertThat(enroll.getEnrollStatus())
                .as("승인 상태를 검증한다")
                .isEqualTo(EnrollStatus.APPROVED),
                () -> assertThat(enroll.isEnrolledSession(session.getSessionId()))
                .as("세션 등록 여부를 검증한다")
                .isTrue()
        );

    }


    @DisplayName("수강 취소 기능을 검증한다")
    @Test
    public void cancelled() {

        //given
        Session session = TestFixture.LIME_SESSION;
        session.setSessionId(new SessionId(22L));
        session.toRecruitingState();
        NsUser user = TestFixture.BADAJIGI;
        //when
        Enroll enroll = session.register(user.getUserCode(), 0);
        enroll.cancel();
        //then
        assertAll("수강신청을 했지만 (운영진 혹은 강사가)취소 기능을 검증한다",
                () -> assertThat(enroll.getEnrollStatus())
                        .as("수강취소 상태를 검증한다")
                        .isEqualTo(EnrollStatus.CANCELED),
                () -> assertThat(enroll.isEnrolledSession(session.getSessionId()))
                        .as("세션 등록 여부를 검증한다")
                        .isTrue()
        );
    }
}