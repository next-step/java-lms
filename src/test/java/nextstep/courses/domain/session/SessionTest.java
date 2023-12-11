package nextstep.courses.domain.session;

import nextstep.courses.CanNotEnrollSameStudentsException;
import nextstep.courses.CannotEnrollStateException;
import nextstep.courses.ExceedMaxAttendanceCountException;
import nextstep.courses.domain.students.Students;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.List;

class SessionTest {

    @DisplayName("한 번 수강신청한 학생은 다시 신청할 수 없습니다.")
    @Test
    void twoEnroll() {
        // given
        Session session = new Session(SessionStatus.ENROLL, 2);
        Students students = makeOneStudents();
        session.bindStudents(students);
        // when
        // then
        Assertions.assertThatThrownBy(() -> session.addStudent(NsUserTest.JAVAJIGI))
                .isInstanceOf(CanNotEnrollSameStudentsException.class)
                .hasMessage("동일한 학생이 2번 수강신청할 수 없습니다.");
    }

    @DisplayName("유료 강의 최대 수강인원을 초과하게 된다면 에러를 발생시킵니다.")
    @Test
    void exceed() {
        // given
        Session session = new Session(SessionStatus.ENROLL, 1);
        Students students = makeOneStudents();
        session.bindStudents(students);

        // when
        // then
        Assertions.assertThatThrownBy(() -> session.addStudent(NsUserTest.SANJIGI))
                .isInstanceOf(ExceedMaxAttendanceCountException.class)
                .hasMessage("이미 최대 수강 인원이 다 찼습니다.");
    }

    @DisplayName("유료 강의 최대 수강인원 미만이라면 유저를 추가합니다.")
    @Test
    void addNotFreeUser() {
        // given
        Session session = new Session(SessionStatus.ENROLL, 2);
        Students students = makeOneStudents();
        session.bindStudents(students);
        // when
        session.addStudent(NsUserTest.SANJIGI);
        // then
        Assertions.assertThat(session.studentSize()).isEqualTo(2);
    }

    @DisplayName("무료 강의라면 유저를 추가합니다.")
    @Test
    void addUser() {
        // given
        Session session = new Session(SessionStatus.ENROLL);
        Students students = makeOneStudents();
        session.bindStudents(students);
        // when
        session.addStudent(NsUserTest.SANJIGI);
        // then
        Assertions.assertThat(session.studentSize()).isEqualTo(2);
    }

    @ParameterizedTest(name = "수강신청 상태가 모집중이 아닌경우 에러를 발생시킵니다.")
    @EnumSource(names = {"FINISH", "PREPARE"})
    void canNotEnroll(SessionStatus status) {
        // given
        Session session = new Session(status, 1);
        Students students = makeOneStudents();
        session.bindStudents(students);
        // when
        // then
        Assertions.assertThatThrownBy(() -> session.addStudent(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotEnrollStateException.class)
                .hasMessage("수강 인원 모집중인 강의가 아닙니다.");
    }

    private Students makeOneStudents() {
        List<NsUser> users = new ArrayList<>();
        users.add(NsUserTest.JAVAJIGI);
        return new Students(users);
    }
}