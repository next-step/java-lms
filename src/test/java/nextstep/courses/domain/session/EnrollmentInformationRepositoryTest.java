package nextstep.courses.domain.session;

import nextstep.courses.infrastructure.JdbcEnrollmentInformationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
class EnrollmentInformationRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private EnrollmentInformationRepository enrollmentInformationRepository;

    @BeforeEach
    void setUp() {
        enrollmentInformationRepository = new JdbcEnrollmentInformationRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("수강 신청 정보 저장 후 조회 테스트")
    void testEnrollmentInformation_saveAndFindBySesionIdAndUserId_ShouldReturnEnrollmentInformation() {
        // given
        Long sessionId = 10L;
        Long userId = 1234L;
        EnrollmentInformation enrollmentInformation = EnrollmentInformation.of(null, sessionId, userId, true);

        // when
        int count = enrollmentInformationRepository.save(enrollmentInformation);

        // then
        assertEquals(count, 1);

        // when
        EnrollmentInformation savedEnrollmentInformation = enrollmentInformationRepository.findBySessionIdAndUserId(sessionId, userId);

        // then
        assertAll(
                "Enrollment Information is correctly loaded",
                () -> assertEquals(savedEnrollmentInformation.getId(), 1L),
                () -> assertEquals(savedEnrollmentInformation.getSessionId(), sessionId),
                () -> assertEquals(savedEnrollmentInformation.getUserId(), userId),
                () -> assertEquals(savedEnrollmentInformation.hasPaid(), true)
        );
    }

}
