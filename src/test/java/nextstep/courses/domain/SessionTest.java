package nextstep.courses.domain;

import nextstep.common.domain.Image;
import nextstep.fixture.TestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.fail;

class SessionTest {

    @BeforeEach
    public void setUp() {
        TestFixture.fixtureInit();
    }

    @DisplayName("강의는 시작일과 종료일을 가진다")
    @Test
    public void startAndEnd() {
        //given
        Session session = TestFixture.LEMON_SESSION;
        //when
        //then
        assertAll("시작일과 종료일이 존재하는지 검증한다",
                () -> assertThat(session.getStartDate())
                        .as("시작일이 존재해야한다")
                        .isNotNull(),
                () -> assertThat(session.getEndDate())
                        .as("종료일이 존재해야한다")
                        .isNotNull()
        );

    }

    @DisplayName("강의는 강의 커버 이미지 정보를 가진다")
    @Test
    public void sessionImage() {
        //given
        Session session = TestFixture.LEMON_SESSION;
        Image image = TestFixture.BLUE_IMAGE;
        //when
        session.registerCoverImage(image);
        //then
        assertThat(session.coverImageUrl())
                .as("커버 이미지가 존재해야한다")
                .isNotEmpty();
    }

    @DisplayName("강의는 무료 강의와 유료 강의로 나뉜다")
    @Test
    public void payedOrFree() {
        //given
        Session freeSession = TestFixture.MINT_SESSION;
        Session paidSession = TestFixture.LEMON_SESSION;
        //when
        freeSession.toFreeSession();
        //then
        assertThat(freeSession.isFreeSession())
                .as("무료세션을 검증한다")
                .isTrue();
        assertThat(paidSession.isFreeSession())
                .as("유료세션을 검증한다")
                .isFalse();
    }

    @DisplayName("강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다")
    @Test
    public void sessionStatus() {
        //given
        Session session1 = TestFixture.LIME_SESSION;
        Session session2 = TestFixture.MINT_SESSION;
        Session session3 = TestFixture.LEMON_SESSION;
        //when
        session1.toPreparingState();
        session2.toRecruitingState();
        session3.toCloseState();
        //then
        assertThat(session1.getStatus())
                .as("준비상태를 갖는다")
                .isEqualTo(SessionStatus.PREPARING);

        assertThat(session2.getStatus())
                .as("모집중 상태를 갖는다")
                .isEqualTo(SessionStatus.RECRUITING);

        assertThat(session3.getStatus())
                .as("종료상태를 갖는다")
                .isEqualTo(SessionStatus.CLOSED);
    }

    @DisplayName("강의 상태가 모집중일 때 강의 신청에 성공한다")
    @Test
    public void enrollCanOnlyOnRecruit() {
        //given
        Session session = TestFixture.LIME_SESSION;
        Enroll enroll = TestFixture.CARSO_ENROL;
        //when
        session.toRecruitingState();
        session.enroll(enroll);
        //then
        assertThat(session.enrollCheck(enroll))
                .as("강의 신청에 성공함을 검증한다")
                .isTrue();
    }

    @DisplayName("강의 상태가 모집중이 아닐 때 강의 신청에 실패한다")
    @Test
    public void enrollCanOnlyOnRecruitFail() {
        //given
        Session session2 = TestFixture.MINT_SESSION;
        //when
        //then
        fail();
    }

    @DisplayName("강의 최대 수강 인원내에서 수강신청이 성공한다")
    @Test
    public void notExceedMaxStudents() {
        //given
        Session session1 = TestFixture.LIME_SESSION;
        //when
        //then
        fail();
    }

    @DisplayName("강의 최대 수강 인원 초과시 수강신청에 실패한다")
    @Test
    public void notExceedMaxStudentsFail() {
        //given
        Session session2 = TestFixture.MINT_SESSION;
        //when
        //then
        fail();
    }


}