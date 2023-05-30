package nextstep.courses.domain;

import nextstep.courses.fixture.SessionFixture;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SessionTest {
    @Test
    @DisplayName("승인된 사용자가 강의 수강 신청 시 수강인원이 1 증가한다")
    void enroll() {
        Session session = SessionFixture.createRecruitingSession();
        SessionUser sessionUser = new SessionUser(NsUserTest.JAVAJIGI.getId(), SessionUserStatus.APPROVAL);
        session.enroll(sessionUser);
        assertThat(session.enrollmentCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("강의 최대 수강 인원을 초과할 수 없다")
    void enrollFail() {
        Session session = SessionFixture.create(SessionProgressStatus.PROGRESSING, SessionRecruitmentStatus.RECRUITING, 1);
        session.enroll(new SessionUser(NsUserTest.SANJIGI.getId(), SessionUserStatus.APPROVAL));
        SessionUser newUser = new SessionUser(NsUserTest.JAVAJIGI.getId());

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> session.enroll(newUser))
                .withMessageMatching(NextStepUsers.MAXIMUM_ENROLLMENT_MESSAGE);
    }

    @Test
    @DisplayName("강의가 모집중이 아닌 경우 신청할 수 없다")
    void enrollFail2() {
        Session session = SessionFixture.create(SessionProgressStatus.END, SessionRecruitmentStatus.NOT_RECRUITING, 1);
        SessionUser sessionUser = new SessionUser(NsUserTest.JAVAJIGI.getId());

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> session.enroll(sessionUser))
                .withMessageMatching(Session.RECRUITMENT_STATUS_MESSAGE);
    }
}
