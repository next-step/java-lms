package nextstep.courses.service;

import static nextstep.courses.domain.session.image.CoverImageTest.COVER_IMAGE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import nextstep.courses.domain.session.image.CoverImage;

@Transactional
@SpringBootTest
class CoverImageServiceTest {

    @Autowired
    private CoverImageService coverImageService;

    @Test
    @DisplayName("새로운 커버 이미지를 생성한다.")
    void Save_NewCoverImage() {
        final Long savedCoverImageId = coverImageService.save(COVER_IMAGE, 1L);
        final CoverImage savedCoverImage = coverImageService.findById(savedCoverImageId);

        assertThat(savedCoverImage.id())
                .isEqualTo(savedCoverImageId);
    }
}
