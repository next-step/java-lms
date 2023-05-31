package nextstep.courses.domain.session;

import nextstep.courses.domain.CourseTest;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    @Test
    @DisplayName(value = "강의는 강의 최대 수강 인원을 초과할 수 없다")
    void test1() {
        Session session = new Session(
                0L, CourseTest.C1,
                LocalDateTime.now(),
                SessionCoverImageTest.image1,
                SessionType.FREE,
                SessionStatus.RECRUITING, 1);

        session.enrollSession(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> {
            session.enrollSession(NsUserTest.SANJIGI);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "강의 수강신청은 강의 상태가 모집중일 때만 가능하다")
    void test2() {
        Session session = new Session(
                1L, CourseTest.C1,
                LocalDateTime.now(),
                SessionCoverImageTest.image1,
                SessionType.FREE,
                SessionStatus.PREPARING, 1);

        assertThatThrownBy(() -> {
            session.enrollSession(NsUserTest.JAVAJIGI);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "수강신청")
    void test3() {
        Session session = new Session(
                2L, CourseTest.C1,
                LocalDateTime.now(),
                SessionCoverImageTest.image1,
                SessionType.FREE,
                SessionStatus.RECRUITING, 1);
        session.enrollSession(NsUserTest.JAVAJIGI);

        assertThat(session.getCurrentUserSize()).isEqualTo(1);
    }
}
