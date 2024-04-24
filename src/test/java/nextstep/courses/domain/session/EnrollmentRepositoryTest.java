package nextstep.courses.domain.session;

import nextstep.courses.infrastructure.JdbcEnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
class EnrollmentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private EnrollmentRepository enrollmentRepository;

    @BeforeEach
    void setUp() {
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("수강 신청 정보 저장 후 조회 테스트")
    void testEnrollment_saveAndFindBySesionIdAndUserId_ShouldReturnEnrollment() {
        // given
        Long sessionId = 10L;
        Long userId = 1234L;
        Enrollment enrollment = Enrollment.of(null, sessionId, userId, true);

        // when
        int count = enrollmentRepository.save(enrollment);

        // then
        assertEquals(count, 1);

        // when
        Enrollment savedEnrollment = enrollmentRepository.findBySessionIdAndUserId(sessionId, userId);

        // then
        assertAll(
                "Enrollment is correctly loaded",
                () -> assertEquals(savedEnrollment.getId(), 1L),
                () -> assertEquals(savedEnrollment.getSessionId(), sessionId),
                () -> assertEquals(savedEnrollment.getUserId(), userId),
                () -> assertEquals(savedEnrollment.hasPaid(), true)
        );
    }

}
