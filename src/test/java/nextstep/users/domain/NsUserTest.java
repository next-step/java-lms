package nextstep.users.domain;

public class NsUserTest {
    public static final NsUser JAVAJIGI = new NsUser(
            1L, "javajigi", "password", "name",
            "javajigi@slipp.net", UserType.USER, UserSessionType.PAY);
    public static final NsUser SANJIGI = new NsUser(
            2L, "sanjigi", "password", "name", "sanjigi@slipp.net",
            UserType.USER, UserSessionType.FREE);
}
