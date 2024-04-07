package nextstep.courses.domain;

import java.util.List;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {

    public static final Session L1 = new Session(List.of(NsUserTest.JAVAJIGI));

    @DisplayName("수강신청")
    @Test
    void 수강신청_기본기능() {
        Session session = new Session();
        session.join(NsUserTest.JAVAJIGI);
        assertThat(session).isEqualTo(L1);
    }


}
