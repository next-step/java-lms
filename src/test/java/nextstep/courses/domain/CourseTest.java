package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Session aSession;
    private Session bSession;

    @BeforeEach
    void setUp() {
        startedAt = LocalDateTime.of(2023, 4, 3, 0, 0);
        endedAt = LocalDateTime.of(2023, 6, 1, 0, 0);
        SessionDuration duration = new SessionDuration(startedAt, endedAt);
        SessionCoverImage coverImage = SessionCoverImage.create("http://test.com/image01");
        aSession = new Session(duration, coverImage, SessionPaymentType.FREE,
                new SessionRegistration(SessionStatus.PREPARING, 5));
        bSession = new Session(duration, coverImage, SessionPaymentType.PAID,
                new SessionRegistration(SessionStatus.PREPARING, 5));
    }

    @Test
    @DisplayName("여러개의 강의를 가질 수 있다.")
    void test01() {
        Course course = new Course(
                1L,
                "테스트강의",
                Arrays.asList(aSession, bSession),
                1L,
                startedAt,
                endedAt
        );
        assertThat(course.getSessions()).hasSize(2);
    }

}
