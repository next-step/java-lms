package nextstep.sessions.service;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionStudents;
import nextstep.sessions.domain.SessionTest;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class SessionServiceTest {

    @Autowired
    SessionService service;

    NsUser user1;
    NsUser user2;

    Session session1;
    Session session2;

    @BeforeEach
    void setUp() {
        user1 = NsUserTest.JAVAJIGI;
        user2 = NsUserTest.SANJIGI;
        session2 = new Session(SessionTest.s2, new SessionStudents(1));
        session1 = new Session(SessionTest.s1, new SessionStudents(1));
    }

    @Test
    @DisplayName(value = "같은 강의를 수강신청할 수 없음")
    void test1() {
        service.enroll(session1.getId(), user1.getUserId());
        assertThatThrownBy(() -> {
            service.enroll(session1.getId(), user1.getUserId());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "모집 중 상태가 아닌 강의는 수강신청할 수 없음")
    void test2() {
        assertThatThrownBy(() -> {
            service.enroll(session2.getId(), user1.getUserId());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "강의 최대 수강 인원을 초과할 수 없음")
    void test3() {
        assertThatThrownBy(() -> {
            service.enroll(session1.getId(), user2.getUserId());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "존재하지 않는 강의는 수강할 수 없다")
    void test4() {
        assertThatThrownBy(() -> {
            service.enroll(999L, user1.getUserId());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "존재하지 않는 사용자는 수강신청할 수 없다")
    void test5() {
        assertThatThrownBy(() -> {
            service.enroll(session2.getId(), "nonExistentUser");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
