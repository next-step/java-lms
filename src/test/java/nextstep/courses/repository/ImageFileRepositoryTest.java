package nextstep.courses.repository;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.ImageFile;
import nextstep.courses.domain.ImageType;
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
        int save = imageFileRepository.save(new ImageFile(1024 * 1024, "jpg", 300, 200));

        assertThat(save).isEqualTo(1);
    }

    @Test
    void find() {
        imageFileRepository.save(new ImageFile(1024 * 1024, "jpg", 300, 200));

        assertThat((imageFileRepository.findById(1L))).isEqualTo(new ImageFile(1L,1024 * 1024, "jpg", 300, 200));
    }
}
