package nextstep.users.domain;

public class NsUserTest {
    public static final NsUser JAVAJIGI = new NsUser.Builder()
            .id(1L)
            .sessionId(1L)
            .userId("javajigi")
            .password("password")
            .name("name")
            .email("javajigi@slipp.net")
            .build();
    public static final NsUser SANJIGI = new NsUser.Builder()
            .id(2L)
            .sessionId(1L)
            .userId("sanjigi")
            .password("password")
            .name("name")
            .email("sanjigi@slipp.net")
            .build();
}
