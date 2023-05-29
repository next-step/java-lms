package nextstep.users.domain;

import java.time.LocalDateTime;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net", LocalDateTime.of(2023, 5, 29, 12, 34, 56), null);
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net", LocalDateTime.of(2023, 5, 29, 12, 34, 56), null);
}
