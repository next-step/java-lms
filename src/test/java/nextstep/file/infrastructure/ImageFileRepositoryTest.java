package nextstep.file.infrastructure;

import nextstep.file.domain.ImageFile;
import nextstep.file.domain.ImageFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcOperations;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class ImageFileRepositoryTest {
	@Autowired
	private JdbcOperations jdbcTemplate;

	private ImageFileRepository imageFileRepository;

	@BeforeEach
	void setUp() {
		imageFileRepository = new JdbcImageFileRepository(jdbcTemplate);
	}

	@Test
	void 등록_성공() {
		assertThat(imageFileRepository.save(new ImageFile(0L, 1024, 200, 300, "jpg"))).isEqualTo(1L);
	}
}
