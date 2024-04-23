package fixture.sessions.domain;

import nextstep.sessions.domain.ChargeStatus;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionStatus;

import java.time.LocalDate;

public class SessionFixture {
    public static Session createSessionWithEnrollment(int capacity, SessionStatus status) {
        return new Session(
                "TDD, 클린 코드 with Java 16기", 2L,
                ChargeStatus.PAID, 800_000,
                capacity, status,
                LocalDate.now(), LocalDate.now().plusDays(30));
    }
    public static Session createSessionWithSessionDate(LocalDate startDate, LocalDate endDate) {
        return new Session(
                "TDD, 클린 코드 with Java 16기", 2L,
                ChargeStatus.PAID, 800_000,
                10, SessionStatus.OPENED,
                startDate, endDate);
    }
}
