package nextstep.courses.domain;

import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.session.FreeSession;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.user.SessionUser;
import nextstep.courses.domain.session.user.SessionUserStatus;
import nextstep.courses.domain.session.user.SessionUsers;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;

class SessionTest {
    Session session;

    @BeforeEach
    void setUp() {
        LocalDate startDate = LocalDate.of(2024, 1, 20);
        LocalDate endDate = LocalDate.of(2024, 3, 20);
        Period period = new Period(startDate, endDate);
        Image image = new Image(1000, 200, 300, "test.jpg", LocalDateTime.now());
        List<SessionUser> sessionUsers = List.of(new SessionUser(1L, 1L));

        session = new FreeSession("축구교실", new Course(), period, List.of(image), SessionUsers.from(sessionUsers));
    }

    @DisplayName("수강 신청은 모집중인 상태에서만 가능하다.")
    @Test
    void enroll() {
        assertThatThrownBy(() -> session.enroll(new NsUser(1L, "asdasd", "asd123", "김모모", "qkqk@qkqk.com"), LocalDate.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("강사는 자신의 강의인 경우 선발된 인원의 신청 상태를 승인할 수 있다. ")
    @Test
    void accessSession() {
        SessionUser sessionUser = new SessionUser(3L, 3L);
        Assertions.assertThatCode(() -> session.accessUser(sessionUser, session.getCreatorId()))
                .doesNotThrowAnyException();
    }
}
