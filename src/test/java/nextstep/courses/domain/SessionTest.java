package nextstep.courses.domain;

import nextstep.users.domain.UserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class SessionTest {
    @DisplayName("무료강의 생성 테스트")
    @Test
    void create_free_session() throws Exception {
        Session.freeOf("test_session", 2L,
                0.5, 300, 200, "jpg",
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                SessionStatus.ENROLLING
        );
    }

    @DisplayName("성공 - 수강신청")
    @Test
    void enroll_session() throws Exception {
        Session paidSession = Session.paidOf("test_session", 2L,
                0.5, 300, 200, "jpg",
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                SessionStatus.ENROLLING, 2,
                800_000
        );
        paidSession.enroll(UserTest.JAVAJIGI);
    }
}