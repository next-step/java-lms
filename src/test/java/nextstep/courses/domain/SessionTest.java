package nextstep.courses.domain;

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

        assertAll(
                () -> {
                    Session session = new FreeSession(
                            period,
                            thumbnail,
                            students);
                    boolean actual = session.isSupport(SessionType.FREE);
                    boolean expected = true;

                    assertThat(actual).isEqualTo(expected);
                },
                () -> {
                    Session session = new PaidSession(
                            period,
                            thumbnail,
                            students,
                            students);
                    boolean actual = session.isSupport(SessionType.PAID);
                    boolean expected = true;

                    assertThat(actual).isEqualTo(expected);
                }
        );
    }
}
