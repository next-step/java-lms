package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollStateException;
import nextstep.courses.ExceedMaxAttendanceCountException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class SessionTest {

    @ParameterizedTest(name = "수강신청 상태가 모집중이 아닌경우 에러를 발생시킵니다.")
    @EnumSource(names = {"FINISH", "PREPARE"})
    void canNotEnroll(SessionStatus status) {
        // given
        Session session = new Session(status);
        // when
        // then
        Assertions.assertThatThrownBy(() -> session.addUser(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotEnrollStateException.class)
                .hasMessage("수강 인원 모집중인 강의가 아닙니다.");
    }

    @DisplayName("무료 강의라면 수강 인원을 바로 1 증가시킵니다.")
    @Test
    void freeSession() {
        // given
        Session session = new Session(SessionStatus.ENROLL);
        // when
        session.addUser(NsUserTest.JAVAJIGI);
        // then
        Assertions.assertThat(session.attendUserCount()).isEqualTo(1);
    }

    @DisplayName("유료 강의인데 최대 수강인원을 초과하게 된다면 에러를 발생시킵니다.")
    @Test
    void exceedMaxAttendance() {
        // given
        Session session = new Session(SessionStatus.ENROLL, 1);
        session.addUser(NsUserTest.JAVAJIGI);
        // when
        // then
        Assertions.assertThatThrownBy(() -> session.addUser(NsUserTest.SANJIGI))
                .isInstanceOf(ExceedMaxAttendanceCountException.class)
                .hasMessage("이미 최대 수강 인원이 다 찼습니다.");
    }

    @DisplayName("유료 강의이면서 수강신청 가능한 인원이라면 수강 신청에 성공합니다.")
    @Test
    void successEnroll() {
        // given
        Session session = new Session(SessionStatus.ENROLL, 2);
        session.addUser(NsUserTest.JAVAJIGI);
        // when
        session.addUser(NsUserTest.SANJIGI);
        // then
        Assertions.assertThat(session.attendUserCount()).isEqualTo(2);
    }
}