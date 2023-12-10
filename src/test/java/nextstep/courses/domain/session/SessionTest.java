package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.exception.CannotEnrollException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {

    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다")
    void session_paid_lecture() throws CannotEnrollException {
        Session session = new Session(Type.PAID, Status.OPEN, 2);
        session.enrollStudent(NsUserTest.JAVAJIGI);
        session.enrollStudent(NsUserTest.SANJIGI);
        assertThatThrownBy(() -> {
            session.enrollStudent(NsUserTest.PARK);
        }).isInstanceOf(CannotEnrollException.class);
    }

    @Test
    @DisplayName("강의는 중복 수강신청할 수 없다")
    void session_duplicate_student() throws CannotEnrollException {
        Session session = new Session(Type.PAID, Status.OPEN, 10);
        session.enrollStudent(NsUserTest.JAVAJIGI);
        session.enrollStudent(NsUserTest.SANJIGI);
        session.enrollStudent(NsUserTest.PARK);
        assertThatThrownBy(() -> {
            session.enrollStudent(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotEnrollException.class);
    }

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다")
    void session_open_status() throws CannotEnrollException {
        Session sessionPreparing = new Session(Type.PAID, Status.PREPARING, 10);
        assertThatThrownBy(() -> {
            sessionPreparing.enrollStudent(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotEnrollException.class);

        Session sessionClose = new Session(Type.PAID, Status.CLOSE, 10);
        assertThatThrownBy(() -> {
            sessionClose.enrollStudent(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotEnrollException.class);
    }
}
