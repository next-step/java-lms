package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaidSessionTest {

    @Test
    void PaidSession_생성자_01() {
        new PaidSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.PREPARING, 100L, 10000L);
    }

    @Test
    void PaidSession_생성자_02() {
        List<NsUser> userList = new ArrayList<>();
        NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
        userList.add(JAVAJIGI);
        userList.add(SANJIGI);

        new PaidSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.PREPARING, userList, 100L, 10000L);
    }
}
