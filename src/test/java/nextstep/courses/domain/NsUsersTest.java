package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NsUsersTest {

    @Test
    void 등록_테스트() {
        NsUsers nsUsers = new NsUsers();
        nsUsers.enroll(NsUserTest.JAVAJIGI);
        assertThat(nsUsers.count()).isEqualTo(1);
    }


}
