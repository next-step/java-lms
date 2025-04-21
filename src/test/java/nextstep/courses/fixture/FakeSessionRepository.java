package nextstep.courses.fixture;

import nextstep.courses.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class FakeSessionRepository implements SessionRepository {

    public static final Session TEST_SESSION = new Session(
            1L,
            new Course(1L),
            SessionStatus.RECRUITING,
            SessionType.PAID,
            new Money(10000L),
            new Capacity(30),
            new Enrollments(),
            LocalDate.now(),
            LocalDate.now().plusDays(7),
            SessionCoverImage.from("imagePath"),
            LocalDateTime.now(),
            LocalDateTime.now()
    );

    @Override
    public Optional<Session> findById(Long id) {
        return Optional.of(TEST_SESSION);
    }
}
