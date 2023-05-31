package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class SessionTest {

    private static final LocalDateTime startedAt = LocalDateTime.now();
    private static final LocalDateTime endedAt = LocalDateTime.now().plusDays(30);
    private static final String sessionCoverImage = "https://edu.nextstep.camp/images/covers/basic/008.jpg";
    private static final SessionCostType sessionCostType = SessionCostType.PAID;
    private static final SessionStatus sessionStatus = SessionStatus.RECRUITING;
    private static final int maxUserCount = 30;

    public static final Session sessionTest1 = new Session(1L, new ArrayList<>(), new SessionPeriod(startedAt, endedAt), sessionCoverImage, sessionCostType, sessionStatus, maxUserCount);


    @DisplayName("강의는 시작일과 종료일을 가진다.")
    @Test
    void 시작일_종료일_확인() {
        assertThat(sessionTest1.startedAt()).isEqualTo(startedAt);
        assertThat(sessionTest1.endedAt()).isEqualTo(endedAt);
    }

    @DisplayName("강의는 강의 커버 이미지 정보를 가진다.")
    @Test
    void 강의_커버_이미지경로_확인() {
        assertThat(sessionTest1.getSessionCoverImage()).isEqualTo(sessionCoverImage);
    }

    @DisplayName("강의는 무료 강의와 유료 강의로 나뉜다.")
    @ParameterizedTest
    @EnumSource(value = SessionCostType.class, names = {"FREE", "PAID"})
    void 강의_타입_확인(SessionCostType sessionCostType) {
        Session session = new Session(1L, new ArrayList<>(), new SessionPeriod(startedAt, endedAt), sessionCoverImage, sessionCostType, sessionStatus, maxUserCount);
        assertThat(session.getSessionCostType()).isEqualTo(sessionCostType);
    }

    @DisplayName("강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다.")
    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"PREPARING", "RECRUITING", "CLOSED"})
    void 강의_상태_확인(SessionStatus sessionStatus) {
        Session session = new Session(1L, new ArrayList<>(), new SessionPeriod(startedAt, endedAt), sessionCoverImage, sessionCostType, sessionStatus, maxUserCount);
        assertThat(session.getSessionStatus()).isEqualTo(sessionStatus);
    }

    @DisplayName("강의 상태가 모집중이 아니면, 수강신청이 불가능")
    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"PREPARING", "CLOSED"})
    void 수강신청_모집중아닌경우_불가능(SessionStatus sessionStatus) {
        Session session = new Session(1L, new ArrayList<>(), new SessionPeriod(startedAt, endedAt), sessionCoverImage, sessionCostType, sessionStatus, maxUserCount);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> session.register(NsUserTest.JAVAJIGI))
                .withMessageMatching("해당 강의는 모집중이 아닙니다.");
    }

    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"RECRUITING"})
    void 수강신청_모집중_가능(SessionStatus sessionStatus) {
        Session session = new Session(1L, new ArrayList<>(), new SessionPeriod(startedAt, endedAt), sessionCoverImage, sessionCostType, sessionStatus, maxUserCount);
        session.register(NsUserTest.JAVAJIGI);
        assertThat(session.getUsers()).hasSize(1).containsOnly(NsUserTest.JAVAJIGI);
    }

    @DisplayName("강의는 강의 최대 수강 인원을 초과할 수 없다.")
    @Test
    void 수강신청_수강인원초과_불가능() {
        int maxUserCount = 1;
        Session session = new Session(1L, new ArrayList<>(), new SessionPeriod(startedAt, endedAt), sessionCoverImage, sessionCostType, sessionStatus, maxUserCount);
        session.register(NsUserTest.JAVAJIGI);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> session.register(NsUserTest.SANJIGI))
                .withMessageMatching("최대 수강 인원을 초과했습니다.");
    }
}

