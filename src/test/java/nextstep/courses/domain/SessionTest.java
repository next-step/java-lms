package nextstep.courses.domain;

import nextstep.courses.domain.code.SessionStatus;
import nextstep.courses.domain.code.SessionType;
import nextstep.courses.domain.strategy.FreeSession;
import nextstep.courses.domain.strategy.PaidSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class SessionTest {

    @Test
    @DisplayName("SeesionType에 따라 인터페이스로 지원을 한다")
    void isSupport() {
        Period period = new Period(LocalDate.of(2023, 11, 24),
                LocalDate.of(2023, 11, 24));
        Thumbnail thumbnail = new Thumbnail("테스트",
                "/home/test.png",
                new Volume(1024L),
                new Size(new Width(300),
                        new Height(200)));
        Students students = new Students();
        Amount amount = new Amount(20000L);

        assertAll(
                () -> {
                    Session session = new FreeSession(
                            period,
                            thumbnail,
                            students,
                            SessionStatus.RECRUITING);

                    boolean actual = session.isSupport(SessionType.FREE);
                    boolean expected = true;

                    assertThat(actual).isEqualTo(expected);
                },
                () -> {

                    Session session = new PaidSession(
                            period,
                            thumbnail,
                            students,
                            students,
                            amount,
                            SessionStatus.RECRUITING);

                    boolean actual = session.isSupport(SessionType.PAID);
                    boolean expected = true;

                    assertThat(actual).isEqualTo(expected);
                }
        );
    }
}
