package nextstep.courses.domain;

import nextstep.courses.domain.session.SessionCapacity;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.fixture.NsUserFixture.nsUser;
import static nextstep.courses.domain.fixture.SessionCapacityFixture.MAX_CAPACITY;
import static nextstep.courses.domain.fixture.SessionCapacityFixture.sessionCapacity;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class SessionCapacityTest {

    @Test
    @DisplayName("[성공] 최대 인원을 초과하지 않는 수강생 목록을 관리한다.")
    void 최대_인원_미만() {
        NsUser user = nsUser();

        SessionCapacity sessionCapacity = sessionCapacity(MAX_CAPACITY);
        assertThatNoException()
                .isThrownBy(() -> sessionCapacity.addUser(user));
    }

}
