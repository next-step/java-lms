package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.NsUsers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnrollmentTest {
    @Test
    @DisplayName("SessionEnrollment 생성")
    void create() {
        assertThat(new Enrollment()).isInstanceOf(Enrollment.class);
    }

    @Test
    @DisplayName("유저 등록")
    void enroll() {
        List<NsUser> users = new ArrayList<>();
        Enrollment actual = new Enrollment(new NsUsers(users), new NsUserLimit(1,SessionPaymentType.PAID));
        actual.enroll(NsUserTest.JAVAJIGI);
        assertThat(users).contains(NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("유저 등록시 한계량을 넘으면 예외발생")
    void enroll_exception() {
        List<NsUser> users = new ArrayList<>();
        Enrollment actual = new Enrollment(new NsUsers(users), new NsUserLimit(0,SessionPaymentType.PAID));
        assertThatThrownBy(()->actual.enroll(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
