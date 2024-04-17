package nextstep.courses.domain;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.MembershipType;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionStatus;
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

        Session session = new Session(0L, "title", startDate, endDate, coverImage, MembershipType.FREE, SessionStatus.PREPARING);
        Assertions.assertThat(session)
                .extracting("id", "title", "startDate", "endDate", "coverImage", "type", "status")
                .containsExactly(0L, "title", startDate, endDate, coverImage, MembershipType.FREE, SessionStatus.PREPARING);
    }
}
