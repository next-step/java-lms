package nextstep.session.infrastructure;

import nextstep.session.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class ImageInfoRepositoryTest {
	public static final ImageInfo I1 = new ImageInfo(1L, new ImageSize(1024), new ImageReSolution(300, 200), ImageType.JPG);

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageInfoRepositoryTest.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private ImageInfoRepository imageInfoRepository;

	@BeforeEach
	void setUp() {
		imageInfoRepository = new JdbcImageInfoRepository(jdbcTemplate);
	}

	@Test
	void findImageInfoById() {
		Optional<ImageInfo> imageInfo = imageInfoRepository.findById(1L);
		assertThat(imageInfo.isEmpty()).isFalse();
		LOGGER.debug("imageInfo: {}", imageInfo.get());
	}

	@Test
	void saveImageInfo() {
		assertThat(imageInfoRepository.save(I1)).isEqualTo(1);
	}

	@Test
	void updateImageInfo() {
		imageInfoRepository.save(I1);
		ImageInfo changeImageInfo = new ImageInfo(1L, new ImageSize(1024), new ImageReSolution(300, 200), ImageType.GIF);
		imageInfoRepository.update(changeImageInfo);
		Optional<ImageInfo> imageInfo = imageInfoRepository.findById(1L);
		assertThat(imageInfo.isEmpty()).isFalse();
		assertThat(imageInfo.get().getImageType()).isEqualTo(changeImageInfo.getImageType());
	}

	@Test
	void deleteImageInfoById() {
		assertThat(imageInfoRepository.deleteById(1L)).isEqualTo(1);
	}

}