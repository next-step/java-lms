package nextstep.courses.domain;

import nextstep.courses.domain.session.PaidSession;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.type.SessionStatus;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PaidSessionTest {
    PaidSession session;
    Period period;
    Image image;

    NsUsers nsUsers;

    @BeforeEach
    void setUp() {
        LocalDate startDate = LocalDate.of(2024, 1, 20);
        LocalDate endDate = LocalDate.of(2024, 3, 20);
        period = new Period(startDate, endDate);
        image = new Image(1000, 200, 300, "test.jpg");
        List<NsUser> userList = new ArrayList<>();
        userList.add(new NsUser());
        userList.add(new NsUser());
        userList.add(new NsUser());
        nsUsers = NsUsers.from(userList);

        session = new PaidSession(new Course(), period, image, nsUsers, 3, 5000L);
    }

    @DisplayName("유료강의는 정해진 인원수를 넘으면 신청할 수 없다.")
    @Test
    void enroll() {
        assertThatThrownBy(() -> session.enroll(new NsUser(), LocalDate.now()));
    }

    @DisplayName("유료강의는 모집중 기간일 때 만 신청할 수 있다.")
    @Test
    void enrollOnlyRecruiting() {
        PaidSession readySession = new PaidSession(1L, new Course(), period, image, SessionStatus.READY, nsUsers, 5, 5000L);
        assertThatThrownBy(() -> readySession.enroll(new NsUser(1L), LocalDate.now()));

        PaidSession paidSession = new PaidSession(1L, new Course(), period, image, SessionStatus.RECRUITING, nsUsers, 5, 5000L);
        assertThatCode(() -> paidSession.enroll(new NsUser(1L), LocalDate.now()))
                .doesNotThrowAnyException();

        PaidSession closedSession = new PaidSession(1L, new Course(), period, image, SessionStatus.CLOSED, nsUsers, 5, 5000L);
        assertThatThrownBy(() -> closedSession.enroll(new NsUser(1L), LocalDate.now()));
    }

    @DisplayName("강의 금액을 반환한다.")
    @Test
    void getAmount() {
        assertThat(session.getAmount()).isEqualTo(5000L);
    }
}
