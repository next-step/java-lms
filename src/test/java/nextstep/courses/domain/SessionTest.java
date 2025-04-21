package nextstep.courses.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {
    @Test
    @DisplayName("강의는 시작일과 종료일을 가진다.")
    void createSession() {
        Period period = new Period(LocalDate.now(), LocalDate.now().plusDays(1));
        Session session = new Session(1L, period);
        Assertions.assertAll(
            () -> assertNotNull(session.startAt()),
            () -> assertNotNull(session.endAt())
        );
    }
}
