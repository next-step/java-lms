package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.info.basic.SessionThumbnail;
import nextstep.courses.domain.session.info.basic.ThumbnailInfo;
import nextstep.courses.dto.ImageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;
import java.util.List;

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
                .width(600)
                .height(400)
                .createdAt(now)
                .updatedAt(now)
                .build();
        List<ImageDto> expectedImages = List.of(expectedImage);

        when(jdbcTemplate.query(anyString(), any(RowMapper.class), any()))
                .thenReturn(expectedImages);

        // when
        SessionThumbnail sessionThumbnail = imageRepository.findThumbnailBySessionId(sessionId);
        ThumbnailInfo actualImage = sessionThumbnail.getThumbnails().get(0);

        // then
        assertThat(actualImage).isNotNull();
        assertThat(actualImage.getFileName().getFullFileName()).isEqualTo(expectedImage.getFileName());
        assertThat(actualImage.getFileSize()).isEqualTo(expectedImage.getFileSize());
        assertThat(actualImage.getSize().getWidth()).isEqualTo(expectedImage.getWidth());
        assertThat(actualImage.getSize().getHeight()).isEqualTo(expectedImage.getHeight());
    }
} 