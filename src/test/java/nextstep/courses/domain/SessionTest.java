package nextstep.courses.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.EnrollmentPolicy;
import nextstep.courses.domain.session.metadata.Period;

public class SessionTest {

    Period period;

    @BeforeEach
    public void setUp() {
        period = new Period(LocalDate.now(), LocalDate.now().plusDays(1));
    }

    @Test
    @DisplayName("강의는 시작일과 종료일을 가진다.")
    void createSession() {
        Session session = new Session(1L, period);
        assertAll(
            () -> assertEquals(session.startAt(), LocalDate.now()),
            () -> assertEquals(session.endAt(), LocalDate.now().plusDays(1))
        );
    }

    @Test
    @DisplayName("강의는 모집중 상태일때만 수강신청이 가능하다")
    void unableToRegister() {
        Session preparingSession = new Session(1L, period);
        Session openSession = new Session(1L, period, SessionStatus.OPEN);
        Session closedSession = new Session(1L, period, SessionStatus.CLOSED);
        assertAll(
            () -> assertFalse(preparingSession.canEnroll()),
            () -> assertTrue(openSession.canEnroll()),
            () -> assertFalse(closedSession.canEnroll())
        );
    }

    @Test
    @DisplayName("강의는 유료 강의와 무료 강의로 나뉜다")
    void paidSessionTest() {
        Session paidSession = new Session(1L, period, SessionStatus.OPEN, EnrollmentPolicy.paid(10000, 2));
        Session freeSession = new Session(2L, period, SessionStatus.OPEN);
        assertAll(
            () -> assertFalse(paidSession.isFree()),
            () -> assertTrue(freeSession.isFree())
        );
    }

}
