package nextstep.courses.domain;

public class SessionsTest {
    public static Sessions ss1 = new Sessions();

    static {
        ss1.addSession(SessionTest.s1);
    }
}
