package nextstep.users.domain;

import nextstep.users.domain.enums.UserStatus;

import java.time.LocalDateTime;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net", UserStatus.SELECTED_FOR_FREE, LocalDateTime.of(2023, 5, 29, 12, 34, 56), null);
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net", UserStatus.NOT_SELECTED, LocalDateTime.of(2023, 5, 29, 12, 34, 56), null);
    public static final User INSTRUCTOR = new User(3L, "instructor", "password", "name", "instructor@slipp.net", LocalDateTime.of(2023, 5, 29, 12, 34, 56), null);
    public static final User DKSWNKK = new User(4L, "dkswnkk", "password", "name", "dkswnkk@slipp.net", UserStatus.SELECTED_FOR_FREE, LocalDateTime.of(2023, 5, 29, 12, 34, 56), null);
    public static final User DKSWNZZ = new User(5L, "dkswnzz", "password", "name", "dkswnzz@slipp.net", LocalDateTime.of(2023, 5, 29, 12, 34, 56), null);
}
