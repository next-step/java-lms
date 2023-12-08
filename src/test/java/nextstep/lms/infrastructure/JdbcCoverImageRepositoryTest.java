package nextstep.lms.infrastructure;

import nextstep.lms.domain.CoverImage;
import nextstep.lms.domain.FileMetadataTest;
import nextstep.lms.domain.FileNameStructureTest;
import nextstep.lms.repository.CoverImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcCoverImageRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void create_read() {
        CoverImage coverImage = this.coverImage();
        int count = coverImageRepository.save(coverImage);
        assertThat(count).isEqualTo(1);
        CoverImage savedCoverImage = coverImageRepository.findById(2L);
        assertThat(coverImage.getName()).isEqualTo(savedCoverImage.getName());
        assertThat(coverImage.getExtension()).isEqualTo(savedCoverImage.getExtension());
        assertThat(coverImage.getFileVolume()).isEqualTo(savedCoverImage.getFileVolume());
        assertThat(coverImage.getWidth()).isEqualTo(savedCoverImage.getWidth());
        assertThat(coverImage.getHeight()).isEqualTo(savedCoverImage.getHeight());
    }

    private CoverImage coverImage() {
        return new CoverImage(0L, FileNameStructureTest.NORMAL_FILE_NAME, FileMetadataTest.NORMAL_FILE_METADATA, LocalDateTime.now(), null);
    }
}