package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;

import static nextstep.Fixtures.aSession;
import static org.assertj.core.api.Assertions.*;

class SessionTest {
    @Test
    @DisplayName("강의는 시작일과 종료일을 가진다.")
    void test02() {
        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime endedAt = startedAt.plusDays(2);

        Session session = aSession().withSessionPeriod(new SessionPeriod(startedAt, endedAt)).build();

        assertThat(session.getSessionPeriod()).isEqualTo(new SessionPeriod(startedAt, endedAt));
    }

    @Test
    @DisplayName("강의는 커버 이미지 정보를 가진다.")
    void test03() {
        String coverImageUrl = "http://edu.nextstep.camp";
        Session session = aSession().withSessionCoverImage(new SessionCoverImage(coverImageUrl)).build();

        assertThat(session.getCoverImageUrl()).isEqualTo(new SessionCoverImage(coverImageUrl));
    }

    @ParameterizedTest(name = "강의는 {0} 강의가 존재한다.")
    @EnumSource(value = SessionBillType.class, names = {"FREE", "PAID"})
    void test04(SessionBillType sessionBillType) {
        Session session = aSession().withSessionBillType(sessionBillType).build();

        assertThat(session.getSessionType()).isEqualTo(sessionBillType);
    }

    @ParameterizedTest(name = "강의 상태 {0} 존재한다.")
    @EnumSource(value = SessionStatus.class, names = {"READY", "OPEN", "CLOSED"})
    void test05(SessionStatus sessionStatus) {
        Session session = aSession().withSessionStatus(sessionStatus).build();

        assertThat(session.getSessionStatus()).isEqualTo(sessionStatus);
    }

    @Test
    @DisplayName("모집중일때만 강의 수강신청이 가능하다.")
    void test11() {
        Session session = aSession().withId(1L)
                                    .withSessionStatus(SessionStatus.OPEN)
                                    .withSessionRecruitStatus(SessionRecruitStatus.RECRUIT)
                                    .withMaxUserCount(10)
                                    .build();

        session.register(NsUserTest.JAVAJIGI);

        assertThat(session.getSessionJoins()).hasSize(1).extracting("session.id", "nsUser.id")
                                             .containsExactly(tuple(1L, 1L));
    }

    @Test
    @DisplayName("강의가 진행중인 상태라도 모집상태가 모집중이면 수강신청이 가능하다.")
    void test12() {
        Session session = aSession().withId(1L)
                                    .withSessionStatus(SessionStatus.READY)
                                    .withSessionRecruitStatus(SessionRecruitStatus.RECRUIT)
                                    .build();

        session.register(NsUserTest.JAVAJIGI);

        assertThat(session.getSessionJoins()).hasSize(1).extracting("session.id", "nsUser.id")
                                             .containsExactly(tuple(1L, 1L));
    }

    @Test
    @DisplayName("강의가 모집중이 아니면 수강신청 할 수 없다.")
    void test13() {
        Session session = aSession().withId(1L)
                                    .withSessionStatus(SessionStatus.OPEN)
                                    .withSessionRecruitStatus(SessionRecruitStatus.NOT_RECRUIT)
                                    .build();

        assertThatThrownBy(() -> session.register(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의가 종료되면 수강신청 할 수 없다.")
    void test14() {
        Session session = aSession().withId(1L)
                                    .withSessionStatus(SessionStatus.CLOSED)
                                    .withSessionRecruitStatus(SessionRecruitStatus.RECRUIT)
                                    .build();

        assertThatThrownBy(() -> session.register(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의 최대 수강인원이 초과하면 등록할 수 없다.")
    void test21() {
        Session session = aSession().withSessionStatus(SessionStatus.OPEN)
                                    .withMaxUserCount(1)
                                    .build();

        session.addUser(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> session.register(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }
}
