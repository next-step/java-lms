package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.image.CoverImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
class SessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("무료 강의 저장 후 조회 테스트")
    void testFreeSession_updateAndFindBySessionId_ShouldReturnFreeSession() {
        SessionPeriod sessionPeriod = SessionPeriod.of(LocalDateTime.of(2024,1,1,0,0, 0),
                LocalDateTime.of(2024,4,1,0,0, 0));
        List<CoverImage> coverImages = List.of(CoverImage.of("jpg", 1024,300,200));

        // given
        Session freeSession = new FreeSession(1L, sessionPeriod, coverImages, SessionStatusEnum.OPEN, 0, true);

        // when
        int count = sessionRepository.save(freeSession);
        Session savedFreeSession = sessionRepository.findBySessionId(1L);

        // then
        assertEquals(count, 1);
        assertAll(
                "read the saved Free Session correctly",
                () -> assertEquals(savedFreeSession.getSessionId(), 1L),
                () -> assertEquals(savedFreeSession.getMaxEnrollments(), 0),
                () -> assertEquals(savedFreeSession.getFee(), 0L),
                () -> assertEquals(savedFreeSession.getSessionPeriod().getStartDate(), LocalDateTime.of(2024,1,1,0,0,0)),
                () -> assertEquals(savedFreeSession.getSessionStatus(), SessionStatusEnum.OPEN),
                () -> assertEquals(savedFreeSession.isOpenForEnrollment(), true)
        );
    }

    @Test
    @DisplayName("유료 강의 저장 후 조회 테스트")
    void testPaidSession_updateAndFindBySessionId_ShouldReturnPaidSession() {
        SessionPeriod sessionPeriod = SessionPeriod.of(LocalDateTime.of(2024,1,1,0,0, 0),
                LocalDateTime.of(2024,4,1,0,0, 0));

        // given
        Session paidSession = new PaidSession(1L, sessionPeriod, SessionStatusEnum.OPEN, true, 0, 100, 10L);

        // when
        int count = sessionRepository.save(paidSession);
        Session savedPaidSession = sessionRepository.findBySessionId(1L);

        // then
        assertEquals(count, 1);
        assertAll(
                "read the saved Paid Session correctly",
                () -> assertEquals(savedPaidSession.getSessionId(), 1L),
                () -> assertEquals(savedPaidSession.getMaxEnrollments(), 100),
                () -> assertEquals(savedPaidSession.getFee(), 10L),
                () -> assertEquals(savedPaidSession.getSessionPeriod().getEndDate(), LocalDateTime.of(2024,4,1,0,0,0)),
                () -> assertEquals(savedPaidSession.getSessionStatus(), SessionStatusEnum.OPEN),
                () -> assertEquals(savedPaidSession.isOpenForEnrollment(), true)
        );
    }

}
