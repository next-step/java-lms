package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionStatus;
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
        Session session = Session.createFreeSession(1L, period, null);

        assertAll(
            () -> assertEquals(session.startAt(), LocalDate.now()),
            () -> assertEquals(session.endAt(), LocalDate.now().plusDays(1))
        );
    }

    @Test
    @DisplayName("종료일 이후에는 수강상태를 변경할 수 없다.")
    void endSession() {
        period = new Period(LocalDate.now().minusDays(2), LocalDate.now().minusDays(1));
        Session session = Session.createFreeSession(1L, period, null);
        assertThatThrownBy(
            session::close
        ).isInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest()
    @CsvSource({
        "PREPARING, false",
        "OPEN, true",
        "CLOSED, false"
    })
    void canEnrollOnlyWhenOpen(SessionStatus status, boolean expected) {
        Session session = Session.createFreeSession(1L, period, null);

        if (status == SessionStatus.OPEN) {
            session.open();
        } else if (status == SessionStatus.CLOSED) {
            session.open();
            session.close();
        }

        assertEquals(expected, session.canEnroll());

    }

    @Test
    @DisplayName("강의는 유료 강의와 무료 강의로 나뉜다")
    void paidSessionTest() {
        Session paidSession = Session.createPaidSession(1L, period, null, Amount.of(10_000), 10);
        Session freeSession = Session.createFreeSession(1L, period, null);

        assertAll(
            () -> assertFalse(paidSession.isFree()),
            () -> assertTrue(freeSession.isFree())
        );
    }

}
