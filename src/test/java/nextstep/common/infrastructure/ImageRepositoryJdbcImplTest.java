package nextstep.common.infrastructure;

import nextstep.common.domain.Image;
import nextstep.common.domain.ImageRepository;
import nextstep.fixture.TestFixture;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ImageRepositoryJdbcImplTest {
    @Autowired ImageRepository imageRepository;

    @DisplayName("저장한다")
    @Test
    public void 저장한다() {
        //given
        Image image = TestFixture.BLUE_IMAGE;
        //when
        Image save = imageRepository.save(image);
        //then
        assertThat(save.getImageId()).as("").isNotNull();
    }
}