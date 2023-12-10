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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        CoverImage coverImage1 = this.coverImage();
        CoverImage coverImage2 = this.coverImage();

        int count = coverImageRepository.save(coverImage1);
        count += coverImageRepository.save(coverImage2);

        List<CoverImage> expectdCoverImages = new ArrayList<>(Arrays.asList(coverImage1, coverImage2));

        assertThat(count).isEqualTo(2);

        List<CoverImage> savedCoverImages = coverImageRepository.findBySessionId(2L);

        for (int index = 0; index < expectdCoverImages.size(); index++) {
            CoverImage expectdCoverImage = expectdCoverImages.get(index);
            CoverImage savedCoverImage = savedCoverImages.get(index);
            assertThat(expectdCoverImage.getName()).isEqualTo(savedCoverImage.getName());
            assertThat(expectdCoverImage.getExtension()).isEqualTo(savedCoverImage.getExtension());
            assertThat(expectdCoverImage.getFileVolume()).isEqualTo(savedCoverImage.getFileVolume());
            assertThat(expectdCoverImage.getWidth()).isEqualTo(savedCoverImage.getWidth());
            assertThat(expectdCoverImage.getHeight()).isEqualTo(savedCoverImage.getHeight());
        }
    }

    private CoverImage coverImage() {
        return new CoverImage(2L, FileNameStructureTest.NORMAL_FILE_NAME, FileMetadataTest.NORMAL_FILE_METADATA);
    }
}