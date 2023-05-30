package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class NsUsersTest {

    @Test
    void 등록_테스트() {
        NsUsers nsUsers = new NsUsers(1);
        nsUsers.enroll(NsUserTest.JAVAJIGI);
        assertThat(nsUsers.count()).isEqualTo(1);
    }

    @Test
    void 최대인원_초과_테스트() {
        NsUsers nsUsers = new NsUsers(0);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> nsUsers.enroll(NsUserTest.JAVAJIGI))
                .withMessageMatching("강의 최대 수강 인원이 초과되었습니다.");
    }
}
