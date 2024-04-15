package nextstep.enrollment.domain;

import static nextstep.enrollment.domain.EnrollmentStatus.APPROVED;
import static nextstep.enrollment.domain.EnrollmentStatus.REGISTERED;
import static nextstep.sessions.domain.SessionProgressStatus.END;
import static nextstep.sessions.domain.SessionProgressStatus.PREPARING;
import static nextstep.sessions.domain.SessionRecruitingStatus.NON_RECRUITING;
import static nextstep.sessions.domain.SessionRecruitingStatus.RECRUITING;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserType.WOOTECAM;
import static nextstep.users.domain.NsUserType.WOOTECO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.Course;
import nextstep.sessions.domain.Session;
import nextstep.users.domain.NsUser;

class EnrollmentTest {

    private static final long SESSION_PRICE = 10000L;

    private NsUser user1;
    private NsUser user2;
    private Course course;
    private Session session;

    @BeforeEach
    void setUp() {
        user1 = new NsUser(3L, "lee", "password", "name", "lee@slipp.net", WOOTECO);
        user2 = new NsUser(4L, "kim", "password", "name", "lee@slipp.net", WOOTECAM);
        course = new Course("TDD, 클린 코드 with Java", 1L);
        session = new Session(2, RECRUITING, PREPARING, SESSION_PRICE, LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2), course);
    }

    @Test
    void 강의_최대_수강_인원을_초과한_경우_수강_신청에_실패한다() {
        enrollAndApprove(user1);
        enrollAndApprove(user2);

        assertThatIllegalArgumentException().isThrownBy(
                () -> new Enrollment(session, new NsUser(3L, "lee", "password", "name", "lee@slipp.net", WOOTECO)).enroll(SESSION_PRICE)
        ).withMessage("수강 인원이 초과되었습니다.");
    }

    @Test
    void 모집중이_아닌_강의인_경우_수강_신청을_하면_실패한다() {
        final Session session = new Session(5, NON_RECRUITING, PREPARING, 100000L, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), course);

        assertThatIllegalArgumentException().isThrownBy(() -> new Enrollment(session, JAVAJIGI).enroll(100000L))
                .withMessage("모집중인 강의가 아닙니다.");
    }

    @Test
    void 시작일이_지난_강의를_수강_신청을_하면_실패한다() {
        final Session session = new Session(5, RECRUITING, PREPARING, 100000L, LocalDateTime.now().plusDays(1), course);

        assertThatIllegalArgumentException().isThrownBy(() -> new Enrollment(session, JAVAJIGI).enroll(100000L))
                .withMessage("시작일이 지난 강의는 수강 신청할 수 없습니다.");
    }

    @Test
    void 결제_금액과_강의_금액이_일치하지_않으면_실패한다() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Enrollment(session, JAVAJIGI).enroll(20000L));
    }

    @Test
    void 무료_강의_수강_신청한다() {
        // given
        final Session freeSession = Session.free(RECRUITING, PREPARING,course);
        final Enrollment enrollment = new Enrollment(freeSession, JAVAJIGI);

        // when
        enrollment.enroll(0L);

        // then
        assertThat(enrollment.getStatus()).isEqualTo(REGISTERED);
    }

    @Test
    void 유료_강의_수강_신청한다() {
        // given
        final Enrollment enrollment = new Enrollment(session, JAVAJIGI);

        // when
        enrollment.enroll(SESSION_PRICE);

        // then
        assertThat(enrollment.getStatus()).isEqualTo(REGISTERED);
    }

    @Test
    void 이미_수강_신청한_강의인_경우_수강_신청에_실패한다() {
        enrollAndApprove(user1);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Enrollment(session, user1).enroll(SESSION_PRICE))
                .withMessage("이미 수강 신청한 사용자입니다.");
    }

    @Test
    void 강의_진행_상태가_END이면_수강_신청에_실패한다() {
        // given
        final Session session = new Session(2, RECRUITING, END, SESSION_PRICE, LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2), course);

        // when
        final Enrollment enrollment = new Enrollment(session, JAVAJIGI);

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> enrollment.enroll(SESSION_PRICE))
                .withMessage("종료된 강의입니다.");
    }

    @Test
    void 선발된_인원인_경우_강사가_승인한다() {
        // given
        final Enrollment enrollment = new Enrollment(session, user1);
        enrollment.enroll(SESSION_PRICE);

        // when
        enrollment.approveBy(JAVAJIGI);

        // then
        assertThat(enrollment.getStatus()).isEqualTo(APPROVED);
    }

    private void enrollAndApprove(final NsUser attendee) {
        final Enrollment enrollment = new Enrollment(session, attendee);
        enrollment.enroll(SESSION_PRICE);
        enrollment.approveBy(JAVAJIGI);
    }
}
