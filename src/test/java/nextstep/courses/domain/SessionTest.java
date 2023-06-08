package nextstep.courses.domain;

import nextstep.courses.domain.builder.EnrollmentBuilder;
import nextstep.courses.domain.builder.SessionBuilder;
import nextstep.courses.domain.enrollment.Student;
import nextstep.courses.domain.enrollment.Students;
import nextstep.courses.domain.fixture.SessionFixtures;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {

    @Test
    void 수강신청에_성공한다() {
        Long sessionId = 1L;
        Session session = new SessionBuilder()
                .withId(sessionId)
                .withEnrollment(
                        new EnrollmentBuilder()
                                .withStatus(SessionStatus.RECRUITING)
                                .withStudents(new Students(10))
                                .build()
                )
                .build();

        Student student = session.enroll(SessionFixtures.USER);
        assertThat(student.getSessionId()).isEqualTo(sessionId);
        assertThat(student.getNsUserId()).isEqualTo(SessionFixtures.USER.getId());
    }
}