package nextstep.courses.domain.session;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.session.type.SessionStatus;
import nextstep.courses.domain.session.user.SessionUsers;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class FreeSessionTest {
    Course course;

    @BeforeEach
    void setUp() {
        Period period = new Period(LocalDate.of(2024, 2, 4), LocalDate.of(2024, 10, 9));
        Image image = new Image(1000, 200, 300, "test.jpg");
        SessionUsers sessionUsers = SessionUsers.from(new ArrayList<>());
        List<Session> sessions = new ArrayList<>();
        sessions.add(new FreeSession(1L, "축구교실", null, period, List.of(image), SessionStatus.RECRUITING, sessionUsers,1L));
        sessions.add(new FreeSession(2L, "축구교실", null, period, List.of(image), SessionStatus.READY, sessionUsers,1L));
        sessions.add(new FreeSession(3L, "축구교실", null, period, List.of(image), SessionStatus.CLOSED, sessionUsers,1L));

        course = new Course(1L, "무료테스트", 1L, new Sessions(sessions), LocalDateTime.of(2024, 1, 10, 1, 1, 1), LocalDateTime.of(2024, 1, 10, 1, 1, 1));
    }

    @DisplayName("무료강의일 경우 결제객체를 생성할 수 없다.")
    @Test
    void toPaymentFail() {
        assertThatThrownBy(() -> course.toPayment(new NsUser(), 1L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("무료강의는 모집중일 때만 신청할 수 있다.")
    @Test
    void enroll() {
        assertThatCode(() -> course.enroll(new NsUser(1L), 1L, LocalDate.now()))
                .doesNotThrowAnyException();
        assertThatThrownBy(() -> course.enroll(new NsUser(1L), 2L, LocalDate.now()));
        assertThatThrownBy(() -> course.enroll(new NsUser(1L), 3L, LocalDate.now()));
    }
}
