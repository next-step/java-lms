package nextstep.courses.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;

class SessionTest {
    private static SessionPeriod PERIOD = new SessionPeriod(
            LocalDate.of(2023, 5, 23),
            LocalDate.of(2023, 5, 30)
    );


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