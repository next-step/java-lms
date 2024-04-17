package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
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
        List<NsUser> userList = new ArrayList<>();
        NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
        userList.add(JAVAJIGI);
        userList.add(SANJIGI);

        new FreeSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.PREPARING, userList);
    }

    @Test
    void 수강신청_정상() {
        FreeSession freeSession = new FreeSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.RECRUITING);
        NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");

        Assertions.assertThat(freeSession.userCount()).isEqualTo(0);
        freeSession.join(JAVAJIGI);
        Assertions.assertThat(freeSession.userCount()).isEqualTo(1);
    }

    @Test
    void 수강신청_예외_모집전() {
        FreeSession freeSession = new FreeSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.PREPARING);
        NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");

        Assertions.assertThatIllegalArgumentException().isThrownBy(()->{
            freeSession.join(JAVAJIGI);
        });
    }

    @Test
    void 수간신청_예외_이미수강중() {
        FreeSession freeSession = new FreeSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.RECRUITING);
        NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        freeSession.join(JAVAJIGI);

        Assertions.assertThatIllegalArgumentException().isThrownBy(()->{
            freeSession.join(JAVAJIGI);
        });
    }
}
