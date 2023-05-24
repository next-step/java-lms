package nextstep.courses.domain;

import nextstep.courses.fixture.SessionFixture;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SessionTest {
    @Test
    @DisplayName("강의 수강 신청 시 수강인원이 1 증가한다")
    void enroll() {
        Session session = SessionFixture.create(SessionStatus.RECRUITING, 1);
        session.enroll(NsUserTest.JAVAJIGI);
        assertThat(session.enrollmentCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("강의 최대 수강 인원을 초과할 수 없다")
    void enrollFail() {
        Session session = SessionFixture.create(SessionStatus.RECRUITING, 1);
        session.enroll(NsUserTest.SANJIGI);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> session.enroll(NsUserTest.JAVAJIGI))
                .withMessageMatching("강의 최대 수강 인원이 초과되었습니다.");
    }

    @Test
    @DisplayName("강의가 모집중이 아닌 경우 신청할 수 없다")
    void enrollFail2() {
        Session session = SessionFixture.create(SessionStatus.PREPARING, 1);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> session.enroll(NsUserTest.JAVAJIGI))
                .withMessageMatching("모집중인 강의가 아닙니다.");
    }
}
