package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.courses.CannotRegisterException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SessionTest {

    Image image;

    @BeforeEach
    void setUp() {
        image = new Image("test.png", 300, 200, 1_000);
    }

    @Test
    void 무료강의_신청() throws CannotRegisterException {
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session.Builder(1L)
                .title("lms")
                .sessionType(SessionType.FREE)
                .image(image)
                .state(SessionState.RECRUITING)
                .sessionDuration(now.plusDays(5), now.plusDays(30))
                .enrollment(Enrollment.createFreeEnrollment(new Students(new ArrayList<>())))
                .build();

        session.register(NsUserTest.JAVAJIGI);
        int expected = 1;

        assertThat(session.countOfEnrolledStudent()).isEqualTo(expected);
    }

    @Test
    void 무료강의_신청_모집중_이외에는_예외발생() {
        LocalDateTime now = LocalDateTime.now();
        Session session1 = new Session.Builder(1L)
                .title("lms")
                .sessionType(SessionType.FREE)
                .image(image)
                .state(SessionState.READY)
                .sessionDuration(now.plusDays(5), now.plusDays(30))
                .enrollment(Enrollment.createFreeEnrollment(new Students(List.of(NsUserTest.JAVAJIGI))))
                .build();
        assertThatThrownBy(() -> session1.register(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotRegisterException.class);

        Session session2 = new Session.Builder(1L)
                .title("lms")
                .sessionType(SessionType.FREE)
                .image(image)
                .state(SessionState.END)
                .sessionDuration(now.plusDays(5), now.plusDays(30))
                .enrollment(Enrollment.createFreeEnrollment(new Students(List.of(NsUserTest.JAVAJIGI))))
                .build();
        assertThatThrownBy(() -> session2.register(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotRegisterException.class);
    }

    @Test
    void 유료강의_신청() throws CannotRegisterException {
        LocalDateTime now = LocalDateTime.now();
        int expected = 1;

        Session session = new Session.Builder(1L)
                .title("lms")
                .sessionType(SessionType.PAID)
                .image(image)
                .state(SessionState.RECRUITING)
                .sessionDuration(now.plusDays(5), now.plusDays(30))
                .enrollment(Enrollment.createPaidEnrollment(new Students(new ArrayList<>()), 10, 5_000))
                .build();
        Payment payment = new Payment("1", 1L, NsUserTest.JAVAJIGI.getId(), 5_000L);
        session.register(NsUserTest.JAVAJIGI, payment);

        assertThat(session.countOfEnrolledStudent()).isEqualTo(expected);
    }
}
