package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SessionAttendeesTest {
    @Test
    @DisplayName("최대 수강 신청자 수가 1명 미만일 경우, IllegalArgumentException 예외 발생")
    void max_number_of_attendees_is_less_than_1_then_throw_IllegalArgumentException() {
        int invalidMaxNumberOfAttendees = 0;
        assertThatThrownBy(() -> new SessionAttendees(invalidMaxNumberOfAttendees))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 수강자 수는 1명 이상이여야 합니다: " + invalidMaxNumberOfAttendees);
    }

    @Test
    @DisplayName("강의 신청을 한 사람이 다시 신청 할 경우, IllegalArgumentException 예외 발생")
    void duplicate_application_then_throw_IllegalArgumentException() {
        // given
        SessionAttendees sessionAttendees = new SessionAttendees(10);
        sessionAttendees.add(NsUserTest.JAVAJIGI);

        // when, then
        assertThatThrownBy(() -> sessionAttendees.add(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 등록된 수강생입니다: " + NsUserTest.JAVAJIGI.getName());
    }

    @Test
    @DisplayName("신청자 수가 최대 수강자 수를 초과할 경우, IllegalArgumentException 예외 발생")
    void maximum_number_of_attendees_is_exceeded_then_throw_IllegalArgumentException() {
        // given
        int maxNumberOfAttendees = 1;
        SessionAttendees sessionAttendees = new SessionAttendees(maxNumberOfAttendees);
        sessionAttendees.add(NsUserTest.JAVAJIGI);

        // when, then
        assertThatThrownBy(() -> sessionAttendees.add(NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수강 가능 인원이 다 찼습니다: " + maxNumberOfAttendees);
    }

    @Test
    @DisplayName("SessionAttendees 객체 생성 시 수강자 인원 수가 최대 수강자 수를 초과할 경우, IllegalArgumentException 예외 발생")
    void maximum_number_of_attendees_is_exceeded_when_create_SessionAttendees_object_then_throw_IllegalArgumentException() {
        // given
        int maxNumberOfAttendees = 1;
        Set<NsUser> attendees = Set.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI);

        // when, then
        assertThatThrownBy(() -> new SessionAttendees(maxNumberOfAttendees, attendees))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수강자 수가 최대 수강자 수(" + maxNumberOfAttendees + ")를 초과했습니다: " + attendees.size());
    }
}