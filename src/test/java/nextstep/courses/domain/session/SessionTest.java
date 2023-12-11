package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.UUID;
import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.image.ImageType;
import nextstep.courses.exception.CannotEnrollException;
import nextstep.courses.exception.InvalidImageException;
import nextstep.courses.exception.InvalidSessionDateException;
import nextstep.courses.exception.InvalidTypeException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {

    Date date;
    Image image;

    @BeforeEach
    void setup() throws InvalidSessionDateException, InvalidTypeException, InvalidImageException {
        date = new Date(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        image = new Image(1024, ImageType.value("gif"), 301, 201);
    }

    @Test
    @DisplayName("강의 시작일은 종료일보다 늦어질 수 없다")
    void session_start_and_end() {
        assertThatThrownBy(() -> {
            new Session(1L, SessionType.PAID, Status.PREPARING, 800_000L, image, 10,
                new Date(LocalDateTime.now().plusSeconds(1),
                    LocalDateTime.now()));
        }).isInstanceOf(InvalidSessionDateException.class);
    }

    @Test
    @DisplayName("강의 오픈 여부를 확인한다")
    void session_isOpen() throws InvalidSessionDateException {
        Session sessionOpen = new Session(1L, SessionType.PAID, Status.OPEN, 800_000L, image, 2,
            date);
        assertThat(sessionOpen.isOpen()).isTrue();

        Session sessionPreparing = new Session(1L, SessionType.PAID, Status.PREPARING, 800_000L,
            image, 2,
            date);
        assertThat(sessionPreparing.isOpen()).isFalse();

        Session sessionClose = new Session(1L, SessionType.PAID, Status.CLOSE, 800_000L, image, 2,
            date);
        assertThat(sessionClose.isOpen()).isFalse();
    }

    @Test
    @DisplayName("강의 중복 수강신청 여부를 확인한다")
    void session_duplicate_user() throws InvalidSessionDateException {
        Session session = new Session(1L, SessionType.PAID, Status.OPEN, 800_000L, image, 2, date);
        session.addStudent(NsUserTest.JAVAJIGI);
        assertThat(session.duplicateUser(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다")
    void session_max_students() throws InvalidSessionDateException {
        Session session = new Session(1L, SessionType.PAID, Status.OPEN, 800_000L, image, 2, date);
        session.addStudent(NsUserTest.JAVAJIGI);
        session.addStudent(NsUserTest.SANJIGI);
        assertThat(session.isMaxStudents()).isTrue();
    }

    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다")
    void session_paid_lecture() throws CannotEnrollException, InvalidSessionDateException {
        Session session = new Session(1L, SessionType.PAID, Status.OPEN, 800_000L, image, 2, date);
        session.enroll(NsUserTest.JAVAJIGI,
            new Payment(UUID.randomUUID().toString(), session.getSessionId(),
                NsUserTest.JAVAJIGI.getId(), 800_000L));
        session.enroll(NsUserTest.SANJIGI,
            new Payment(UUID.randomUUID().toString(), session.getSessionId(),
                NsUserTest.SANJIGI.getId(), 800_000L));

        assertThatThrownBy(() -> {
            session.enroll(NsUserTest.PARK,
                new Payment(UUID.randomUUID().toString(), session.getSessionId(),
                    NsUserTest.PARK.getId(), 800_000L));
        }).isInstanceOf(CannotEnrollException.class);
    }

    @Test
    @DisplayName("강의는 중복 수강신청할 수 없다")
    void session_duplicate_student() throws CannotEnrollException, InvalidSessionDateException {
        Session session = new Session(1L, SessionType.PAID, Status.OPEN, 800_000L, image, 10, date);
        session.enroll(NsUserTest.JAVAJIGI,
            new Payment(UUID.randomUUID().toString(), session.getSessionId(),
                NsUserTest.JAVAJIGI.getId(), 800_000L));
        session.enroll(NsUserTest.SANJIGI,
            new Payment(UUID.randomUUID().toString(), session.getSessionId(),
                NsUserTest.SANJIGI.getId(), 800_000L));
        session.enroll(NsUserTest.PARK,
            new Payment(UUID.randomUUID().toString(), session.getSessionId(),
                NsUserTest.PARK.getId(), 800_000L));

        assertThatThrownBy(() -> {
            session.enroll(NsUserTest.SANJIGI,
                new Payment(UUID.randomUUID().toString(), session.getSessionId(),
                    NsUserTest.SANJIGI.getId(), 800_000L));
        }).isInstanceOf(CannotEnrollException.class);
    }

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다")
    void session_open_status() throws InvalidSessionDateException {
        Session sessionPreparing = new Session(1L, SessionType.PAID, Status.PREPARING, 800_000L,
            image, 10,
            date);
        assertThatThrownBy(() -> {
            sessionPreparing.enroll(NsUserTest.JAVAJIGI,
                new Payment(UUID.randomUUID().toString(), sessionPreparing.getSessionId(),
                    NsUserTest.JAVAJIGI.getId(), 800_000L));
        }).isInstanceOf(CannotEnrollException.class);

        Session sessionClose = new Session(2L, SessionType.PAID, Status.CLOSE, 800_000L, image, 10,
            date);
        assertThatThrownBy(() -> {
            sessionClose.enroll(NsUserTest.JAVAJIGI,
                new Payment(UUID.randomUUID().toString(), sessionClose.getSessionId(),
                    NsUserTest.JAVAJIGI.getId(), 800_000L));
        }).isInstanceOf(CannotEnrollException.class);
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다")
    void session_paid_session() throws InvalidSessionDateException {
        Session session = new Session(1L, SessionType.PAID, Status.OPEN, 800_000L, image, 2, date);
        NsUser user = NsUserTest.JAVAJIGI;
        Payment payment = new Payment(UUID.randomUUID().toString(), session.getSessionId(),
            user.getId(), 799_999L);

        Assertions.assertThatThrownBy(() -> {
            session.enroll(user, payment);
        }).isInstanceOf(CannotEnrollException.class);
    }
}
