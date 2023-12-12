package nextstep.courses.domain.session;

import nextstep.courses.MaxStudentsNumberExceededException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaidSessionTest {

    @Test
    public void 최대_수강인원_초과() {
        Session session = new PaidSession(1);
        assertThatThrownBy(() -> {
            session.register(NsUserTest.JAVAJIGI);
            session.register(NsUserTest.SANJIGI);
        }).isInstanceOf(MaxStudentsNumberExceededException.class);
    }
}
