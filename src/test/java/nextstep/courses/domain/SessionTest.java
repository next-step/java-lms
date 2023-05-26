package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;

import static nextstep.Fixtures.SessionFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    @DisplayName("강의 기간")
    @Test
    void period() {
        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime endedAt = startedAt.plusDays(2);

        Session session = testSession1().sessionPeriod(new SessionPeriod(startedAt, endedAt)).build();

        assertThat(session.getSessionPeriod()).isEqualTo(new SessionPeriod(startedAt, endedAt));
    }

    @Test
    @DisplayName("강의 커버 이미지 정보")
    void coverImage() {
        String coverImageUrl = "http://nextstep.tdd";
        Session session = testSession1().sessionCoverImage(new SessionCoverImage(coverImageUrl)).build();

        assertThat(session.getCoverImageUrl()).isEqualTo(new SessionCoverImage(coverImageUrl));
    }

    @ParameterizedTest(name = "강의결제 유형 {0}")
    @EnumSource(value = SessionBillingType.class, names = {"FREE", "PAID"})
    void sessionBillingType(SessionBillingType sessionBillType) {
        Session session = testSession1().sessionBillType(sessionBillType).build();

        assertThat(session.getSessionType()).isEqualTo(sessionBillType);
    }

    @ParameterizedTest(name = "강의 상태 {0}")
    @EnumSource(value = SessionStatusType.class, names = {"READY", "OPEN", "CLOSED"})
    void sessionStatusType(SessionStatusType sessionStatusType) {
        Session session = testSession1().sessionStatus(sessionStatusType).build();

        assertThat(session.getSessionStatus()).isEqualTo(sessionStatusType);
    }

    @DisplayName("모집중일시 수강신청 테스트")
    @Test
    void openStatus() {
        Session session = testSession1().sessionStatus(SessionStatusType.OPEN)
                .maxUserCount(10)
                .build();

        session.register(NsUserTest.JAVAJIGI);

        assertThat(session.getUsers()).hasSize(1).containsExactly(NsUserTest.JAVAJIGI);
    }

    @DisplayName("준비중일 때 수강신청 시 오류")
    @Test
    void readyStatus() {
        Session session = testSession1().sessionStatus(SessionStatusType.READY)
                .maxUserCount(10)
                .build();

        assertThatThrownBy(() -> session.register(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("종료일 때 수강신청 시 오류")
    @Test
    void closedStatus() {
        Session session = testSession1().sessionStatus(SessionStatusType.CLOSED)
                .maxUserCount(10)
                .build();

        assertThatThrownBy(() -> session.register(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("강의 최대 수강인원 달성 후 신청 시 오류")
    @Test
    void maximumUser() {
        Session session = testSession1().sessionStatus(SessionStatusType.OPEN)
                .maxUserCount(1)
                .build();

        session.addUser(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> session.register(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }
}
