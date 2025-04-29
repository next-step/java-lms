package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.entity.SessionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@JdbcTest
class JdbcSessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @DisplayName("강의 저장")
    @Test
    void testSave() {
        SessionEntity sessionEntity = createSessionEntity(null, 1L);

        assertDoesNotThrow(() -> sessionRepository.save(sessionEntity));
    }

    @DisplayName("강의 아이디로 조회")
    @Test
    void testFindById() {
        SessionEntity sessionEntity = createSessionEntity(null, 1L);

        long generatedId = sessionRepository.save(sessionEntity);
        assertThat(sessionRepository.findById(generatedId)).isNotNull();
    }

    @DisplayName("과정 ID로 모든 강의 조회")
    @Test
    void testFindAllByCourseId() {
        Long courseId = 1L;
        SessionEntity sessionEntity1 = createSessionEntity(null, courseId);
        SessionEntity sessionEntity2 = createSessionEntity(null, courseId);

        sessionRepository.save(sessionEntity1);
        sessionRepository.save(sessionEntity2);

        List<SessionEntity> sessions = sessionRepository.findAllByCourseId(courseId);

        assertThat(sessions).hasSize(2);
    }

    @DisplayName("강의 삭제")
    @Test
    void testDelete() {
        SessionEntity sessionEntity = createSessionEntity(null, 1L);
        long generatedId = sessionRepository.save(sessionEntity);

        assertDoesNotThrow(() -> sessionRepository.delete(generatedId));
    }


    private SessionEntity createSessionEntity(Long id, Long courseId) {
        return SessionEntity.builder()
            .id(id)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deleted(false)
            .courseId(courseId)
            .fee(200_000L)
            .capacity(80)
            .imageUrl("http://test")
            .imageType("JPG")
            .startDate(LocalDateTime.now())
            .endDate(LocalDateTime.now())
            .type("PAID")
            .status("ONGOING")
            .enrollStatus("ENROLLING")
            .build();
    }
}
