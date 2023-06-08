package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;

import static nextstep.fixtures.SessionFixtures.*;
import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    @DisplayName("강의 기간")
    @Test
    void period() {
        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime endedAt = startedAt.plusDays(7);

        Session session = testSession1();

        assertThat(session.getSessionPeriod()).isEqualTo(new SessionPeriod(startedAt, endedAt));
    }

    @Test
    @DisplayName("강의 커버 이미지 정보")
    void coverImage() {
        String coverImageUrl = "https://nextstep.tdd";
        Session session = testSession1();

        assertThat(session.getSessionCoverImage()).isEqualTo(coverImageUrl);
    }

    @ParameterizedTest(name = "강의결제 유형 {0}")
    @EnumSource(value = SessionBilling.class ,names = {"FREE"})
    void sessionBillingType(SessionBilling sessionBillType) {
        Session session = testSession1();

        assertThat(session.getSessionBilling()).isEqualTo(sessionBillType);
    }

    @ParameterizedTest(name = "강의결제 유형 {0}")
    @EnumSource(value = SessionBilling.class ,names = {"PAID"})
    void sessionBillingType2(SessionBilling sessionBillType) {
        Session session = testSession2();

        assertThat(session.getSessionBilling()).isEqualTo(sessionBillType);
    }

    @ParameterizedTest(name = "강의 상태 {0}")
    @EnumSource(value = SessionStatus.class ,names = {"READY"})
    void sessionStatusType(SessionStatus sessionStatusType) {
        Session session = testSession2();

        assertThat(session.getSessionStatus()).isEqualTo(sessionStatusType);
    }

    @ParameterizedTest(name = "강의 상태 {0}")
    @EnumSource(value = SessionStatus.class ,names = {"OPEN"})
    void sessionStatusType2(SessionStatus sessionStatusType) {
        Session session = testSession1();

        assertThat(session.getSessionStatus()).isEqualTo(sessionStatusType);
    }

    @ParameterizedTest(name = "강의 상태 {0}")
    @EnumSource(value = SessionStatus.class ,names = {"CLOSED"})
    void sessionStatusType3(SessionStatus sessionStatusType) {
        Session session = testSession3();

        assertThat(session.getSessionStatus()).isEqualTo(sessionStatusType);
    }
}
