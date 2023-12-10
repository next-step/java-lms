package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.Type;
import nextstep.courses.exception.CannotEnrollException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {

    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다")
    void session_paid_lecture() throws CannotEnrollException {
        Session session = new Session(Type.PAID, 2);
        session.enrollStudent(NsUserTest.JAVAJIGI);
        session.enrollStudent(NsUserTest.SANJIGI);
        assertThatThrownBy(() -> {
            session.enrollStudent(NsUserTest.PARK);
        }).isInstanceOf(CannotEnrollException.class);
    }

    @Test
    @DisplayName("강의는 중복 수강신청할 수 없다")
    void session_duplicate_student() throws CannotEnrollException {
        Session session = new Session(Type.PAID, 10);
        session.enrollStudent(NsUserTest.JAVAJIGI);
        session.enrollStudent(NsUserTest.SANJIGI);
        session.enrollStudent(NsUserTest.PARK);
        assertThatThrownBy(() -> {
            session.enrollStudent(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotEnrollException.class);
    }
}
