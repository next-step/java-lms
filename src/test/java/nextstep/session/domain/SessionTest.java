package nextstep.session.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import nextstep.session.CannotEnrollException;
import nextstep.session.InvalidEnrollmentPolicyException;
import nextstep.session.InvalidImageConditionsException;
import nextstep.session.StudentAlreadyEnrolledException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SessionTest {

    private static SessionSchedule sessionSchedule;
    private static SessionCoverImage coverImage;

    @BeforeAll
    public static void setUp() throws InvalidImageConditionsException {
        sessionSchedule = new SessionSchedule(LocalDate.of(2024, 6, 1),
            LocalDate.of(2024, 12, 31));

        coverImage = new SessionCoverImage(900, 600, 10000, "png");
    }

    @Test
    public void 강의_신청()
        throws CannotEnrollException, InvalidEnrollmentPolicyException, StudentAlreadyEnrolledException {
        Student student = new Student(1L, 1L, 2L);
        Session session = Session.createPaidSession(1L, 2L, "객체지향강의", sessionSchedule, coverImage,
            SessionStatus.RECRUITING, 500, 50000);

        session.enroll(student, 50000);
        assertThat(session.enrolledStudentCount()).isEqualTo(1);
    }

    @Test
    public void 모집중인_강의만_신청_가능() throws InvalidEnrollmentPolicyException {
        Student student = new Student(1L, 1L, 1L);
        Session session = Session.createPaidSession(1L, 2L, "객체지향강의", sessionSchedule, coverImage,
            SessionStatus.PREPARING, 100, 50000);
        assertThatThrownBy(() ->
            session.enroll(student, 50000)).isInstanceOf(CannotEnrollException.class)
            .hasMessageContaining("현재 모집중인 강의가 아닙니다.");
    }

    @Test
    public void 유료강의의_경우_수강료와_지불금액이_일치해야_신청_가능() throws InvalidEnrollmentPolicyException {
        Student student = new Student(1L, 1L, 1L);
        Session session = Session.createPaidSession(1L, 2L, "객체지향강의", sessionSchedule, coverImage,
            SessionStatus.RECRUITING, 100, 50000);
        assertThatThrownBy(() ->
            session.enroll(student, 20000)).isInstanceOf(CannotEnrollException.class)
            .hasMessageContaining("수강료와 지불금액이 일치하지 않습니다.");
    }


}
