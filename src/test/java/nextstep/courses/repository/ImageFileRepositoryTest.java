package nextstep.courses.repository;

import nextstep.courses.domain.ImageFile;
import nextstep.courses.infrastructure.JdbcImageFileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(JdbcImageFileRepository.class)
public class ImageFileRepositoryTest {

    @Autowired
    ImageFileRepository imageFileRepository;

    @Test
    void save() {
        Long imageId = imageFileRepository.save(new ImageFile(1024 * 1024, "jpg", 300, 200));

        assertThat(imageId).isNotNull();
    }

    @Test
    void find() {
        Long imageId = imageFileRepository.save(new ImageFile(1024 * 1024, "jpg", 300, 200));

        assertThat((imageFileRepository.findById(imageId))).isEqualTo(new ImageFile(1L,1024 * 1024, "jpg", 300, 200));
    }
}
