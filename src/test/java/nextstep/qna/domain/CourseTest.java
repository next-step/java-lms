package nextstep.qna.domain;

import nextstep.courses.domain.Course;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.SessionTest.S1;
import static nextstep.qna.domain.SessionTest.S2;
import static nextstep.qna.domain.SessionTest.S3;
import static nextstep.qna.domain.SessionTest.S4;
import static nextstep.qna.domain.SessionTest.S5;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseTest {
    public static final Course C1;

    static {
        C1 = new Course();
        C1.addSession(S1);
        C1.addSession(S2);
        C1.addSession(S3);
        C1.addSession(S4);
        C1.addSession(S5);
    }

    @Test
    void getSessionInfo() {
        List<Session> sessions = C1.sessions();
        assertTrue(sessions.contains(S1));
        assertTrue(sessions.contains(S2));
        assertTrue(sessions.contains(S3));
        assertTrue(sessions.contains(S4));
        assertTrue(sessions.contains(S5));
    }
}
