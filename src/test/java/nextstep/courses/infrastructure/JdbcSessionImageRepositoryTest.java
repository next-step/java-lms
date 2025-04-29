package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.image.SessionImageRepository;
import nextstep.courses.entity.SessionImageEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@JdbcTest
public class JdbcSessionImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionImageRepository sessionImageRepository;

    @BeforeEach
    void setUp() {
        sessionImageRepository = new JdbcSessionImageRepository(jdbcTemplate);
    }

    @DisplayName("강의 이미지 저장")
    @Test
    void testSave() {
        SessionImageEntity sessionImageEntity = createSessionImageEntity(1L, 3L);

        assertDoesNotThrow(() -> sessionImageRepository.save(sessionImageEntity));
    }

    @DisplayName("강의 이미지 아이디로 조회")
    @Test
    void testFindById() {
        SessionImageEntity sessionImageEntity = createSessionImageEntity(null, 1L);
        long generatedId = sessionImageRepository.save(sessionImageEntity);
        assertThat(sessionImageRepository.findById(generatedId)).isNotNull();
    }

    @DisplayName("강의 ID로 모든 강의 이미지 조회")
    @Test
    void testFindAllBySessionId() {
        sessionImageRepository.save(createSessionImageEntity(1L, 2L));
        sessionImageRepository.save(createSessionImageEntity(2L, 2L));

        assertThat(sessionImageRepository.findAllBySessionId(2L)).hasSize(2);
    }

    @DisplayName("강의 이미지 삭제")
    @Test
    void testDelete() {
        SessionImageEntity sessionImageEntity = createSessionImageEntity(null, 1L);
        long generatedId = sessionImageRepository.save(sessionImageEntity);

        assertDoesNotThrow(() -> sessionImageRepository.delete(generatedId));
    }

    private SessionImageEntity createSessionImageEntity(Long id, Long sessionId) {
        return SessionImageEntity.builder()
            .id(id)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deleted(false)
            .imageUrl("https://test")
            .imageType("PNG")
            .sessionId(sessionId)
            .build();
    }
}
