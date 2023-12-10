package nextstep.courses.domain.enrollment;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.UUID;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.Status;
import nextstep.courses.domain.session.Type;
import nextstep.courses.exception.CannotEnrollException;
import nextstep.courses.exception.InvalidSessionDateException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EnrollmentTest {

    Enrollment enrollment;

    @BeforeEach
    void setup() {
        enrollment = new Enrollment();
    }

    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다")
    void enrollment_paid_lecture() throws CannotEnrollException, InvalidSessionDateException {
        Session session = new Session(1L, Type.PAID, Status.OPEN, 800_000L, 2, LocalDateTime.now(),
            LocalDateTime.now().plusMonths(1));
        enrollment.enroll(session, NsUserTest.JAVAJIGI,
            new Payment(UUID.randomUUID().toString(), session.getSessionId(),
                NsUserTest.JAVAJIGI.getId(), 800_000L));
        enrollment.enroll(session, NsUserTest.SANJIGI,
            new Payment(UUID.randomUUID().toString(), session.getSessionId(),
                NsUserTest.SANJIGI.getId(), 800_000L));

        assertThatThrownBy(() -> {
            enrollment.enroll(session, NsUserTest.PARK,
                new Payment(UUID.randomUUID().toString(), session.getSessionId(),
                    NsUserTest.PARK.getId(), 800_000L));
        }).isInstanceOf(CannotEnrollException.class);
    }

    @Test
    @DisplayName("강의는 중복 수강신청할 수 없다")
    void enrollment_duplicate_student() throws CannotEnrollException, InvalidSessionDateException {
        Session session = new Session(1L, Type.PAID, Status.OPEN, 800_000L, 10, LocalDateTime.now(),
            LocalDateTime.now().plusMonths(1));
        enrollment.enroll(session, NsUserTest.JAVAJIGI,
            new Payment(UUID.randomUUID().toString(), session.getSessionId(),
                NsUserTest.JAVAJIGI.getId(), 800_000L));
        enrollment.enroll(session, NsUserTest.SANJIGI,
            new Payment(UUID.randomUUID().toString(), session.getSessionId(),
                NsUserTest.SANJIGI.getId(), 800_000L));
        enrollment.enroll(session, NsUserTest.PARK,
            new Payment(UUID.randomUUID().toString(), session.getSessionId(),
                NsUserTest.PARK.getId(), 800_000L));

        assertThatThrownBy(() -> {
            enrollment.enroll(session, NsUserTest.SANJIGI,
                new Payment(UUID.randomUUID().toString(), session.getSessionId(),
                    NsUserTest.SANJIGI.getId(), 800_000L));
        }).isInstanceOf(CannotEnrollException.class);
    }

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다")
    void enrollment_open_status() throws InvalidSessionDateException {
        Session sessionPreparing = new Session(1L, Type.PAID, Status.PREPARING, 800_000L, 10,
            LocalDateTime.now(),
            LocalDateTime.now().plusMonths(1));
        assertThatThrownBy(() -> {
            enrollment.enroll(sessionPreparing, NsUserTest.JAVAJIGI,
                new Payment(UUID.randomUUID().toString(), sessionPreparing.getSessionId(),
                    NsUserTest.JAVAJIGI.getId(), 800_000L));
        }).isInstanceOf(CannotEnrollException.class);

        Session sessionClose = new Session(2L, Type.PAID, Status.CLOSE, 800_000L, 10,
            LocalDateTime.now(),
            LocalDateTime.now().plusMonths(1));
        assertThatThrownBy(() -> {
            enrollment.enroll(sessionPreparing, NsUserTest.JAVAJIGI,
                new Payment(UUID.randomUUID().toString(), sessionClose.getSessionId(),
                    NsUserTest.JAVAJIGI.getId(), 800_000L));
        }).isInstanceOf(CannotEnrollException.class);
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다")
    void enrollment_paid_session() throws InvalidSessionDateException {
        Session session = new Session(1L, Type.PAID, Status.OPEN, 800_000L, 2, LocalDateTime.now(),
            LocalDateTime.now().plusMonths(1));
        NsUser user = NsUserTest.JAVAJIGI;
        Payment payment = new Payment(UUID.randomUUID().toString(), session.getSessionId(),
            user.getId(), 799_999L);

        Assertions.assertThatThrownBy(() -> {
            enrollment.enroll(session, user, payment);
        }).isInstanceOf(CannotEnrollException.class);
    }
}
