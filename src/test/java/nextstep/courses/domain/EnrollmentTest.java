package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class EnrollmentTest {

    @Test
    void 수강신청은_모집중일때만_가능() {
        Enrollment E1 = new Enrollment(SessionStatus.RECRUITING, new NsUsers(1));
        Enrollment E2 = new Enrollment(SessionStatus.PREPARING, new NsUsers(1));
        Enrollment E3 = new Enrollment(SessionStatus.END, new NsUsers(1));
        assertThat(E1.enrollNsUser(NsUserTest.JAVAJIGI).countNsUsers()).isEqualTo(1);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> E2.enrollNsUser(NsUserTest.JAVAJIGI))
                .withMessageMatching("강의가 모집중 일때만 등록 가능합니다.");


        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> E2.enrollNsUser(NsUserTest.JAVAJIGI))
                .withMessageMatching("강의가 모집중 일때만 등록 가능합니다.");
    }

    @Test
    void 강의는_강의_최대_수강_인원을_초과할_수_없다() {
        Enrollment E1 = new Enrollment(SessionStatus.RECRUITING, new NsUsers(1));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    E1.enrollNsUser(NsUserTest.JAVAJIGI);
                    E1.enrollNsUser(NsUserTest.SANJIGI);
                })
                .withMessageMatching("강의 최대 수강 인원이 초과되었습니다.");
    }


}
