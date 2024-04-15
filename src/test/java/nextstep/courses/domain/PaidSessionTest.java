package nextstep.courses.domain;

import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.session.PaidSession;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.type.SessionStatus;
import nextstep.courses.domain.session.user.SessionUser;
import nextstep.courses.domain.session.user.SessionUsers;
import nextstep.users.domain.NsUser;
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

    SessionUsers sessionUser;

    @BeforeEach
    void setUp() {
        LocalDate startDate = LocalDate.of(2024, 1, 20);
        LocalDate endDate = LocalDate.of(2099, 3, 20);
        period = new Period(startDate, endDate);
        image = new Image(1000, 200, 300, "test.jpg");
        List<SessionUser> userList = new ArrayList<>();
        userList.add(new SessionUser(1L, 1L));
        userList.add(new SessionUser(2L, 2L));
        userList.add(new SessionUser(3L, 3L));
        userList.add(new SessionUser(4L, 4L));
        sessionUser = SessionUsers.from(userList);

        session = new PaidSession(new Course(), "축구교실", period, List.of(image), sessionUser, 3, 5000L);
    }

    @DisplayName("유료강의는 정해진 인원수를 넘으면 신청할 수 없다.")
    @Test
    void enroll() {
        assertThatThrownBy(() -> session.enroll(new NsUser(), LocalDate.now()));
    }

    @DisplayName("유료강의는 모집중 기간일 때 만 신청할 수 있다.")
    @Test
    void enrollOnlyRecruiting() {
        PaidSession readySession = new PaidSession(1L, "축구교실", new Course(), period, List.of(image), SessionStatus.READY, sessionUser, 5, 5000L, 1L);
        assertThatThrownBy(() -> readySession.enroll(new NsUser(1L), LocalDate.now()));

        PaidSession paidSession = new PaidSession(1L, "축구교실", new Course(), period, List.of(image), SessionStatus.RECRUITING, sessionUser, 5, 5000L, 1L);
        assertThatCode(() -> paidSession.enroll(new NsUser(1L), LocalDate.now()))
                .doesNotThrowAnyException();

        PaidSession closedSession = new PaidSession(1L, "축구교실", new Course(), period, List.of(image), SessionStatus.CLOSED, sessionUser, 5, 5000L, 1L);
        assertThatThrownBy(() -> closedSession.enroll(new NsUser(1L), LocalDate.now()));
    }

    @DisplayName("강의 금액을 반환한다.")
    @Test
    void getAmount() {
        assertThat(session.getAmount()).isEqualTo(5000L);
    }
}
