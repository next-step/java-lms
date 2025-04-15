package nextstep.courses.infrastructure;

import nextstep.courses.dto.ImageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JdbcImageRepositoryTest {
    private JdbcImageRepository imageRepository;
    private JdbcOperations jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcOperations.class);
        imageRepository = new JdbcImageRepository(jdbcTemplate);
    }

    @Test
    void findBySessionId() {
        // given
        Long sessionId = 1L;
        LocalDateTime now = LocalDateTime.now();
        ImageDto expectedImage = ImageDto.builder()
                .id(1L)
                .sessionId(sessionId)
                .fileName("test.jpg")
                .fileSize(1024L)
                .width(800)
                .height(600)
                .createdAt(now)
                .updatedAt(now)
                .build();

        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), any()))
                .thenReturn(expectedImage);

        // when
        ImageDto actualImage = imageRepository.findBySessionId(sessionId);

        // then
        assertThat(actualImage).isNotNull();
        assertThat(actualImage.getId()).isEqualTo(expectedImage.getId());
        assertThat(actualImage.getSessionId()).isEqualTo(expectedImage.getSessionId());
        assertThat(actualImage.getFileName()).isEqualTo(expectedImage.getFileName());
        assertThat(actualImage.getFileSize()).isEqualTo(expectedImage.getFileSize());
        assertThat(actualImage.getWidth()).isEqualTo(expectedImage.getWidth());
        assertThat(actualImage.getHeight()).isEqualTo(expectedImage.getHeight());
        assertThat(actualImage.getCreatedAt()).isEqualTo(expectedImage.getCreatedAt());
        assertThat(actualImage.getUpdatedAt()).isEqualTo(expectedImage.getUpdatedAt());
    }
} 