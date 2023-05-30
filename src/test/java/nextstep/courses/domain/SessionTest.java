package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class SessionTest {

    private final LocalDateTime startedAt = LocalDateTime.now();
    private final LocalDateTime endedAt = LocalDateTime.now().plusDays(30);
    private final String sessionCoverImage = "https://edu.nextstep.camp/images/covers/basic/008.jpg";
    private final String sessionCostType = "PAID";
    private final int maxUserCount = 30;


    @DisplayName("강의는 시작일과 종료일을 가진다.")
    @Test
    void 시작일_종료일_확인() {

        Session session = new Session(1L, new ArrayList<>(), new SessionPeriod(startedAt, endedAt), sessionCoverImage, sessionCostType, "RECRUITING", maxUserCount);
        assertThat(session.startedAt()).isEqualTo(startedAt);
        assertThat(session.endedAt()).isEqualTo(endedAt);
    }

    @DisplayName("강의는 강의 커버 이미지 정보를 가진다.")
    @Test
    void 강의_커버_이미지경로_확인() {
        Session session = new Session(1L, new ArrayList<>(), new SessionPeriod(startedAt, endedAt), sessionCoverImage, sessionCostType, "RECRUITING", maxUserCount);
        assertThat(session.getSessionCoverImage()).isEqualTo(sessionCoverImage);
    }

    @DisplayName("강의는 무료 강의와 유료 강의로 나뉜다.")
    @ParameterizedTest
    @ValueSource(strings = {"무료", "유료"})
    void 강의_타입_확인(String type) {
        Session session = new Session(1L, new ArrayList<>(), new SessionPeriod(startedAt, endedAt), sessionCoverImage, type, "RECRUITING", maxUserCount);
        assertThat(session.getSessionCostType()).isEqualTo(SessionCostType.of(type));
    }

    @DisplayName("강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다.")
    @ParameterizedTest
    @ValueSource(strings = {"PREPARING, RECRUITING, CLOSED"})
    void 강의_상태_확인(String sessionStatus) {
        Session session = new Session(1L, new ArrayList<>(), new SessionPeriod(startedAt, endedAt), sessionCoverImage, sessionCostType, sessionStatus, maxUserCount);
        assertThat(session.getSessionStatus()).isEqualTo(sessionStatus);
    }

    @DisplayName("강의 상태가 모집중이 아니면, 수강신청이 불가능")
    @ParameterizedTest
    @ValueSource(strings = {"PREPARING, CLOSED"})
    void 수강신청_모집중아닌경우_불가능(String sessionStatus) {
        Session session = new Session(1L, new ArrayList<>(), new SessionPeriod(startedAt, endedAt), sessionCoverImage, sessionCostType, sessionStatus, maxUserCount);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> session.register(NsUserTest.JAVAJIGI))
                .withMessageMatching("");
    }

    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
    @ParameterizedTest
    @ValueSource(strings = {"RECRUITING"})
    void 수강신청_모집중_가능(String sessionStatus){
        Session session = new Session(1L, new ArrayList<>(), new SessionPeriod(startedAt, endedAt), sessionCoverImage, sessionCostType, sessionStatus, maxUserCount);
        session.register(NsUserTest.JAVAJIGI);
        assertThat(session.getUsers()).hasSize(1).containsOnly(NsUserTest.JAVAJIGI);
    }

    @DisplayName("강의는 강의 최대 수강 인원을 초과할 수 없다.")
    @Test
    void 수강신청_수강인원초과_불가능() {
        int maxUserCount = 1;
        Session session = new Session(1L, new ArrayList<>(),new SessionPeriod(startedAt, endedAt), sessionCoverImage, sessionCostType, "RECRUITING", maxUserCount);
        session.register(NsUserTest.JAVAJIGI);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> session.register(NsUserTest.SANJIGI))
                .withMessageMatching("최대 수강 인원을 초과했습니다.");
    }
}

