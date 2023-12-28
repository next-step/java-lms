package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

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
    @DisplayName("유저 등록시 한계량을 넘으면 예외던짐")
    void enroll_exception() {
        List<NsUser> users = new ArrayList<>();
        Enrollment actual = new Enrollment(new NsUsers(users), new NsUserLimit(0,SessionPaymentType.PAID));
        assertThatThrownBy(()->actual.enroll(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("새 유저 멤버들로 교체, 멤버만 교체함")
    void replaceUsers() {
        List<NsUser> actualUsers = new ArrayList<>();
        List<NsUser> expectedUsers = new ArrayList<>(List.of(NsUserTest.JAVAJIGI,NsUserTest.SANJIGI));
        Enrollment actual = new Enrollment(new NsUsers(actualUsers), new NsUserLimit(0,SessionPaymentType.FREE));
        actual.replaceUsers(new NsUsers(expectedUsers));
        assertIterableEquals(actualUsers, expectedUsers);
    }
}
