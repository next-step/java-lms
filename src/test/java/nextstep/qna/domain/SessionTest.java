package nextstep.qna.domain;

import nextstep.qna.domain.session.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @Test
    void test() {
        Session session = new Session();
        assertNotNull(session);
    }

}