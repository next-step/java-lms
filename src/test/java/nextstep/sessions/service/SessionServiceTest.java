package nextstep.sessions.service;

import nextstep.sessions.domain.*;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
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
    Session session3;
    Session session4;

    @BeforeEach
    void setUp() {
        user1 = NsUserTest.JAVAJIGI;
        user2 = NsUserTest.SANJIGI;

        session1 = new Session(SessionTest.s1, new SessionStudents(1));
        session2 = new Session(SessionTest.s2, new SessionStudents(1));
        session3 = new Session(SessionTest.s3, new SessionStudents(2));
        session4 = new Session(SessionTest.s4, new SessionStudents(2));

        service.save(session1);
        service.save(session2);
        service.save(session3);
        service.save(session4);
    }

    @Test
    @DisplayName(value = "같은 강의를 수강신청할 수 없음")
    void test1() {
        service.enrollStudent(session1.getId(), user1.getUserId());
        assertThatThrownBy(() -> {
            service.enrollStudent(session1.getId(), user1.getUserId());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "비 모집 중 상태인 강의는 수강신청할 수 없음")
    void test2() {
        assertThatThrownBy(() -> {
            service.enrollStudent(session2.getId(), user1.getUserId());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "강의 진행 상태가 종료일 경우 수강신청할 수 없음")
    void test3() {
        assertThatThrownBy(() -> {
            service.enrollStudent(session4.getId(), user1.getUserId());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "강의 최대 수강 인원을 초과할 수 없음")
    void test4() {
        assertThatThrownBy(() -> {
            service.enrollStudent(session1.getId(), user2.getUserId());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "존재하지 않는 강의는 수강할 수 없다")
    void test5() {
        assertThatThrownBy(() -> {
            service.enrollStudent(999L, user2.getUserId());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "존재하지 않는 사용자는 수강신청할 수 없다")
    void test6() {
        assertThatThrownBy(() -> {
            service.enrollStudent(session2.getId(), "nonExistentUser");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "cover image 를 등록하고 조회할 수 있다")
    void test7() {
        service.saveCoverImage(session1.getId(), SessionCoverImageTest.image1);

        Session session = service.getSession(session1);
        assertThat(session.getSessionCoverImage()).isEqualTo(SessionCoverImageTest.image1);
    }

    @Test
    @DisplayName(value = "cover image 를 등록하고 학생을 등록하고 조회할 수 있다")
    void test8() {
        service.saveCoverImage(session3.getId(), SessionCoverImageTest.image3);
        service.enrollStudent(session3.getId(), NsUserTest.JAVAJIGI.getUserId());

        Session session = service.getSession(session3);
        SessionStudents sessionStudents = session.getSessionStudents();
        SessionStudents sessionStudents1 = service.getSessionOfStudent(session).getSessionStudents();

        assertThat(sessionStudents).isEqualTo(sessionStudents1);
        assertThat(session.getSessionCoverImage()).isEqualTo(SessionCoverImageTest.image3);
    }
}
