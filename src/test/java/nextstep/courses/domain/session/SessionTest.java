package nextstep.courses.domain.session;

import nextstep.courses.CanNotEnrollSameNsUserException;
import nextstep.courses.CannotEnrollStateException;
import nextstep.courses.ExceedMaxAttendanceCountException;
import nextstep.courses.domain.sessionuser.SessionUser;
import nextstep.courses.domain.sessionuser.UserType;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class SessionTest {

    @DisplayName("강사가 신청하면 인원에 상관없이 신청 가능합니다.")
    @Test
    void enrollTutor() {
        // given
        Session session = new Session(1L, 1L, SessionStatus.PREPARE, LocalDateTime.now(), LocalDateTime.now(), false, 10);
        SessionUser tutor = new SessionUser(NsUserTest.JAVAJIGI.getId(), session.getId(), UserType.TUTOR, false);
        // when
        session.addSessionUser(tutor);
        // then
        Assertions.assertThat(session.tutorsSize()).isEqualTo(1);
    }

    @DisplayName("한 번 수강신청한 학생은 다시 신청할 수 없습니다.")
    @Test
    void twoEnroll() {
        // given
        Session session = new Session(1L, 1L, SessionStatus.PREPARE, SessionEnrollStatus.ENROLL, LocalDateTime.now(), LocalDateTime.now(), false, 10);
        SessionUser student = makeOneStudents();
        session.addSessionUser(student);
        // when
        // then
        Assertions.assertThatThrownBy(() -> session.addSessionUser(new SessionUser(NsUserTest.JAVAJIGI.getId(), 1L, UserType.STUDENT)))
                .isInstanceOf(CanNotEnrollSameNsUserException.class)
                .hasMessage("동일한 사람이 2번 신청할 수 없습니다.");
    }

    @DisplayName("유료 강의 최대 수강인원을 초과하게 된다면 에러를 발생시킵니다.")
    @Test
    void exceed() {
        // given
        Session session = new Session(1L, 1L, SessionStatus.PREPARE, SessionEnrollStatus.ENROLL, LocalDateTime.now(), LocalDateTime.now(), false, 1);
        SessionUser student = makeOneStudents();
        session.addSessionUser(student);
        // when
        // then
        Assertions.assertThatThrownBy(() -> session.addSessionUser(new SessionUser(NsUserTest.SANJIGI.getId(), 1L, UserType.STUDENT)))
                .isInstanceOf(ExceedMaxAttendanceCountException.class)
                .hasMessage("이미 최대 수강 인원이 다 찼습니다.");
    }

    @DisplayName("유료 강의 최대 수강인원 미만이라면 유저를 추가합니다.")
    @Test
    void addNotFreeUser() {
        // given
        Session session = new Session(1L, 1L, SessionStatus.PREPARE, SessionEnrollStatus.ENROLL, LocalDateTime.now(), LocalDateTime.now(), false, 2);
        SessionUser student = makeOneStudents();
        session.addSessionUser(student);
        // when
        session.addSessionUser(new SessionUser(NsUserTest.SANJIGI.getId(), 1L, UserType.STUDENT));
        // then
        Assertions.assertThat(session.studentSize()).isEqualTo(2);
    }

    @DisplayName("무료 강의라면 유저를 추가합니다.")
    @Test
    void addUser() {
        // given
        Session session = new Session(1L, 1L, SessionStatus.PREPARE, SessionEnrollStatus.ENROLL, LocalDateTime.now(), LocalDateTime.now(), true, null);
        SessionUser student = makeOneStudents();
        // when
        session.addSessionUser(student);
        // then
        Assertions.assertThat(session.studentSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("수강신청 상태가 모집중이 아닌경우 에러를 발생시킵니다.")
    void canNotEnroll() {
        // given
        Session session = new Session(1L, 1L, SessionStatus.PREPARE, SessionEnrollStatus.NOT_ENROLL, LocalDateTime.now(), LocalDateTime.now(), true, null);
        SessionUser student = makeOneStudents();
        // when
        // then
        Assertions.assertThatThrownBy(() -> session.addSessionUser(student))
                .isInstanceOf(CannotEnrollStateException.class)
                .hasMessage("수강 인원 모집중인 강의가 아닙니다.");
    }

    private SessionUser makeOneStudents() {
        return new SessionUser(NsUserTest.JAVAJIGI.getId(), 1L, UserType.STUDENT);
    }
}