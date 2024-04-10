package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;

class EnrolledUsersTest {
    @Test
    @DisplayName("isDuplicatedUser(): user가 이미 등록되어 있으면 true를 그렇지 않으면 false를 반환한다.")
    void testIsDuplicatedUser() {
        EnrolledUsers enrolledUsers = new EnrolledUsers(List.of(JAVAJIGI));

        assertThat(enrolledUsers.isDuplicatedUser(JAVAJIGI)).isTrue();
        assertThat(enrolledUsers.isDuplicatedUser(SANJIGI)).isFalse();
    }
}
