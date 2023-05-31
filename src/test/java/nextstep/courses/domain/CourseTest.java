package nextstep.courses.domain;

import nextstep.sessions.domain.SessionBuilder;
import nextstep.sessions.domain.SessionCoverImage;
import nextstep.sessions.domain.SessionDuration;
import nextstep.sessions.domain.SessionPaymentType;
import nextstep.sessions.domain.SessionRegistrationBuilder;
import nextstep.sessions.domain.SessionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

    private SessionBuilder preparingSession;

    @BeforeEach
    void setUp() {
        LocalDateTime startedAt = LocalDateTime.of(2023, 4, 3, 0, 0);
        LocalDateTime endedAt = LocalDateTime.of(2023, 6, 1, 0, 0);
        preparingSession = SessionBuilder.aSession()
                .withDuration(new SessionDuration(startedAt, endedAt))
                .withCoverImage(SessionCoverImage.create("http://test.com/image01"))
                .with(SessionRegistrationBuilder.aRegistration()
                        .withStatus(SessionStatus.PREPARING)
                        .withStudentCapacity(5));
    }

    @Test
    @DisplayName("여러개의 강의를 가질 수 있다.")
    void test01() {
        Course course = CourseBuilder.aCourse()
                .withId(1L)
                .withTitle("테스트강의")
                .with(preparingSession.but().withPaymentType(SessionPaymentType.FREE))
                .with(preparingSession.but().withPaymentType(SessionPaymentType.PAID))
                .withCreatorId(1L)
                .withCreatedAt(LocalDateTime.of(2023, 4, 2, 0, 0))
                .build();
        assertThat(course.getSessions()).hasSize(2);
    }

}
