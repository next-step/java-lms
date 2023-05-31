package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.session.*;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EnrollmentTest {

    private Session session;

    @BeforeEach
    void setUp() {
        session = new Session(0L, null, null, new SessionCapacity(0, 10),
                "TDD, 클린코드", SessionType.PAY, SessionStatus.READY, RecruitmentStatus.OPEN,
                LocalDateTime.now(), null);
    }

    @Test
    void 수강신청을_승인한다() {
        // given
        Enrollment enrollment = new Enrollment(session, NsUserTest.JAVAJIGI, EnrollmentStatus.PENDING);

        // when
        enrollment.approve();

        // then
        assertThat(enrollment.getStatus()).isEqualTo(EnrollmentStatus.APPROVED);
    }

    @Test
    void 수강신청을_거부한다() {
        // given
        Enrollment enrollment = new Enrollment(session, NsUserTest.SANJIGI, EnrollmentStatus.PENDING);

        // when
        enrollment.approve();

        // then
        assertThat(enrollment.getStatus()).isEqualTo(EnrollmentStatus.CANCELED);
    }
}
