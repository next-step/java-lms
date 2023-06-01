package nextstep.courses.domain;

import nextstep.courses.exception.CannotRegisterException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
    private static SessionPeriod PERIOD = new SessionPeriod(
            LocalDate.of(2023, 5, 23),
            LocalDate.of(2023, 5, 30)
    );


    @Test
    public void 강의_최대_수강_인원을_초과할_수_없다() throws Exception {
        Session session = new Session(
                new ImageInfo("test.test.test"),
                PERIOD,
                SessionType.FREE,
                SessionStatus.RECRUITING,
                1
        );

        session.register();
        assertThatThrownBy(session::register)
                .isInstanceOf(CannotRegisterException.class);
    }

    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"RECRUITING"}, mode = EnumSource.Mode.EXCLUDE)
    public void 현재_모집중인_강의만_수강할_수_있다(SessionStatus status) throws Exception {
        Session session = new Session(
                new ImageInfo("test.test.test"),
                PERIOD,
                SessionType.FREE,
                status,
                1
        );

        assertThatThrownBy(session::register)
                .isInstanceOf(CannotRegisterException.class);
    }
}