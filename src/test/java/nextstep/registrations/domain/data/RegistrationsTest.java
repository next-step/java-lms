package nextstep.registrations.domain.data;

import java.time.LocalDateTime;
import java.util.List;

import nextstep.courses.domain.Course;
import nextstep.payments.domain.Payment;
import nextstep.registrations.domain.exception.RegistrationException;
import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.type.SessionType;
import nextstep.sessions.domain.data.vo.CoverImage;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RegistrationsTest {


    @Test
    void 강의_최대_인원_초과() {
        Session session = new Session(
            new Course("클린 코드 과정", 1L),
            "클린 코드 17기",
            SessionType.PAY,
            800000,
            1,
            new CoverImage("cover-image.png", 102400, 300, 200, "/images/sessions/"),
            LocalDateTime.now(),
            LocalDateTime.now().plusDays(30)
        );
        Registrations registrations = new Registrations(
            List.of(
                new Registration(session, NsUserTest.JAVAJIGI, new Payment()),
                new Registration(session, NsUserTest.SANJIGI, new Payment())
            )
        );

        assertThatThrownBy(registrations::validateRegistration)
            .isInstanceOf(RegistrationException.class)
            .hasMessage("강의 최대 인원을 초과했습니다.");
    }
}
