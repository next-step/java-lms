package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaidSessionTest {
    PaidSession session;

    @BeforeEach
    void setUp() {
        LocalDate startDate = LocalDate.of(2024, 1, 20);
        LocalDate endDate = LocalDate.of(2024, 3, 20);
        Session.Period period = new Session.Period(startDate, endDate);
        Image image = new Image(1000, 200, 300, "test.jpg");
        List<NsUser> nsUsers = List.of(new NsUser(), new NsUser(), new NsUser());

        session = new PaidSession(new Course(), period, image, NsUsers.from(nsUsers), 3, 5000L);
    }

    @DisplayName("유료강의는 정해진 인원수를 넘으면 신청할 수 없다.")
    @Test
    void enroll() {
        Assertions.assertThatThrownBy(() -> session.enroll(new NsUser()));
    }

    @DisplayName("강의 금액을 반환한다.")
    @Test
    void getAmount() {
        Assertions.assertThat(session.getAmount()).isEqualTo(5000L);
    }
}
