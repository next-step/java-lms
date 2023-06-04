package nextstep.courses.domain.session;


public class SessionsTest {
    public static Sessions ss1 = new Sessions();

    static {
        ss1.addSession(SessionTest.readySession);
    }
}
