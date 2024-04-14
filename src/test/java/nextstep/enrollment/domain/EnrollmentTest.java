package nextstep.enrollment.domain;

import static nextstep.sessions.domain.SessionProgressStatus.PREPARING;
import static nextstep.sessions.domain.SessionRecruitingStatus.NON_RECRUITING;
import static nextstep.sessions.domain.SessionRecruitingStatus.RECRUITING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.Course;
import nextstep.sessions.domain.Session;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

class EnrollmentTest {

    private static final long SESSION_PRICE = 10000L;

    private Course course;
    private Session session;

    @BeforeEach
    void setUp() {
        course = new Course("TDD, 클린 코드 with Java", 1L);
        session = new Session(2, RECRUITING, PREPARING, SESSION_PRICE, LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2), course);
    }

    @Test
    void 강의_최대_수강_인원을_초과한_경우_수강_신청에_실패한다() {
        new Enrollment(session, NsUserTest.JAVAJIGI).enroll(SESSION_PRICE);
        new Enrollment(session, NsUserTest.SANJIGI).enroll(SESSION_PRICE);

        assertThatIllegalArgumentException().isThrownBy(
                () -> new Enrollment(session, new NsUser(3L, "lee", "password", "name", "lee@slipp.net")).enroll(SESSION_PRICE)
        ).withMessage("수강 인원이 초과되었습니다.");
    }

    @Test
    void 모집중이_아닌_강의인_경우_수강_신청을_하면_실패한다() {
        final Session session = new Session(5, NON_RECRUITING, PREPARING, 100000L, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), course);

        assertThatIllegalArgumentException().isThrownBy(() -> new Enrollment(session, NsUserTest.JAVAJIGI).enroll(100000L))
                .withMessage("모집중인 강의가 아닙니다.");
    }

    @Test
    void 시작일이_지난_강의를_수강_신청을_하면_실패한다() {
        final Session session = new Session(5, RECRUITING, PREPARING, 100000L, LocalDateTime.now().plusDays(1), course);

        assertThatIllegalArgumentException().isThrownBy(() -> new Enrollment(session, NsUserTest.JAVAJIGI).enroll(100000L))
                .withMessage("시작일이 지난 강의는 수강 신청할 수 없습니다.");
    }

    @Test
    void 결제_금액과_강의_금액이_일치하지_않으면_실패한다() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Enrollment(session, NsUserTest.JAVAJIGI).enroll(20000L));
    }

    @Test
    void 무료_강의_수강_신청한다() {
        // given
        final Session freeSession = Session.free(RECRUITING, PREPARING,course);

        // when
        new Enrollment(freeSession, NsUserTest.JAVAJIGI).enroll(0L);

        // then
        assertThat(freeSession.getEnrollments()).hasSize(1)
                .extracting("attendee")
                .containsExactly(NsUserTest.JAVAJIGI);
    }

    @Test
    void 유료_강의_수강_신청한다() {
        new Enrollment(session, NsUserTest.JAVAJIGI).enroll(SESSION_PRICE);

        assertThat(session.getEnrollments()).hasSize(1)
                .extracting("attendee")
                .containsExactly(NsUserTest.JAVAJIGI);
    }

    @Test
    void 이미_수강_신청한_강의인_경우_수강_신청에_실패한다() {
        new Enrollment(session, NsUserTest.JAVAJIGI).enroll(SESSION_PRICE);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Enrollment(session, new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net")).enroll(SESSION_PRICE))
                .withMessage("이미 수강 신청한 사용자입니다.");
    }
}
