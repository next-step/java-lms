package nextstep.courses.domain.sessionuser;

import nextstep.courses.CannotEnrollStateException;
import nextstep.courses.ExceedMaxAttendanceCountException;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class SessionUsersTest {

    private Course course;
    private Period period;

    @BeforeEach
    void setUp() {
        course = new Course(1L, "course", 1L, LocalDateTime.now().minusHours(1L), LocalDateTime.now().plusHours(1L));
        period = new Period(LocalDateTime.now(), LocalDateTime.now().plusMinutes(30L));
    }

    @DisplayName("유료 강의 최대 수강인원을 초과하게 된다면 에러를 발생시킵니다.")
    @Test
    void exceed() {
        // given
        Session session = new Session(SessionStatus.ENROLL, 1);
        SessionUsers sessionUsers = new SessionUsers(List.of(new SessionUser(NsUserTest.JAVAJIGI, session)));

//        sessionUsers.addUser(NsUserTest.JAVAJIGI, session);
        // when
        // then
        Assertions.assertThatThrownBy(() -> sessionUsers.addUser(NsUserTest.SANJIGI, session))
                .isInstanceOf(ExceedMaxAttendanceCountException.class)
                .hasMessage("이미 최대 수강 인원이 다 찼습니다.");
    }

    @DisplayName("유료 강의 최대 수강인원 미만이라면 유저를 추가합니다.")
    @Test
    void addNotFreeUser() {
        // given
        Session session = new Session(SessionStatus.ENROLL, 1);
        SessionUsers sessionUsers = new SessionUsers(List.of(new SessionUser(NsUserTest.JAVAJIGI, session)));
        // when
        // then
        Assertions.assertThat(sessionUsers.totalAttendUsersCount()).isEqualTo(1);
    }

    @DisplayName("무료 강의라면 유저를 추가합니다.")
    @Test
    void addUser() {
        // given
        Session session = new Session(SessionStatus.ENROLL);
        SessionUsers sessionUsers = new SessionUsers(List.of(new SessionUser(NsUserTest.JAVAJIGI, session)));
        // when
        // then
        Assertions.assertThat(sessionUsers.totalAttendUsersCount()).isEqualTo(1);
    }

    @ParameterizedTest(name = "수강신청 상태가 모집중이 아닌경우 에러를 발생시킵니다.")
    @EnumSource(names = {"FINISH", "PREPARE"})
    void canNotEnroll(SessionStatus status) {
        // given
        Session session = new Session(status, 1);
        SessionUsers sessionUsers = new SessionUsers(new ArrayList<>());
        // when
        // then
        Assertions.assertThatThrownBy(() -> sessionUsers.addUser(NsUserTest.JAVAJIGI, session))
                .isInstanceOf(CannotEnrollStateException.class)
                .hasMessage("수강 인원 모집중인 강의가 아닙니다.");
    }
}