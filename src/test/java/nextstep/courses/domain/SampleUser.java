package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class SampleUser {
    public static final LocalDateTime createDate = LocalDateTime.of(2023, 5, 27, 19, 30, 30);
    public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net", createDate, null);
    public static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net", createDate, null);
    public static final NsUser WOOK = new NsUser(3L, "wook", "password", "name", "wook@gmail.com");
}
