package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class SessionTest {
    @Test
    void Session_생성자_기본() {
        new Session();
    }

    @Test
    void Session_생성자_전체() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        CoverImage coverImage = new CoverImage();

        Session session = new Session(0L, "title", startDate, endDate, coverImage, SessionStatus.PREPARING);
        Assertions.assertThat(session)
                .extracting("id", "title", "startDate", "endDate", "coverImage", "status")
                .containsExactly(0L, "title", startDate, endDate, coverImage, SessionStatus.PREPARING);
    }

    @Test
    void Session_수강신청() {
        Session freeSession = new Session(0L, "freeSession", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.RECRUITING);
        NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");

        Assertions.assertThat(freeSession.userCount()).isEqualTo(0);
        freeSession.join(JAVAJIGI);
        Assertions.assertThat(freeSession.userCount()).isEqualTo(1);
    }

    @Test
    void Session_수강신청_예외_인원초과() {
        Session paidSession = new Session(0L, "paidSession", LocalDate.now(), LocalDate.now(), new CoverImage(), 800000L, 0L, SessionStatus.RECRUITING);
        NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");

        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            paidSession.join(JAVAJIGI);
        });
    }
}
