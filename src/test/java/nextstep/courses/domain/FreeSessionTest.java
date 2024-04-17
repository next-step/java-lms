package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FreeSessionTest {
    @Test
    void 생성자_01() {
        new FreeSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.PREPARING);
    }

    @Test
    void 생성자_02() {
        System.out.println(ImageExtension.GIF.name());
        List<NsUser> userList = new ArrayList<>();
        NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
        userList.add(JAVAJIGI);
        userList.add(SANJIGI);

        new FreeSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.PREPARING, userList);
    }
}
