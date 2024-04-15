package nextstep.courses.domain;

import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.session.FreeSession;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SessionTest {
    Session session;

    @BeforeEach
    void setUp() {
        LocalDate startDate = LocalDate.of(2024, 1, 20);
        LocalDate endDate = LocalDate.of(2024, 3, 20);
        Period period = new Period(startDate, endDate);
        Image image = new Image(1000, 200, 300, "test.jpg");
        List<NsUser> nsUsers = List.of(new NsUser());

        session = new FreeSession("축구교실", new Course(), period, List.of(image), NsUsers.from(nsUsers));
    }

    @DisplayName("수강 신청은 모집중인 상태에서만 가능하다.")
    @Test
    void enroll() {
        assertThatThrownBy(() -> session.enroll(new NsUser(1L, "asdasd", "asd123", "김모모", "qkqk@qkqk.com"), LocalDate.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
