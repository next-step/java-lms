package nextstep.image.domain;

import nextstep.fixture.TestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ImageRepositoryTest {
    @Autowired
    ImageRepository imageRepository;

    @DisplayName("저장한다")
    @Test
    public void 저장한다() {
        //given
        Image image = TestFixture.BLUE_IMAGE;
        //when
        Image save = imageRepository.save(image);
        Image find = imageRepository.findByImageId(save.getImageId()).orElseThrow(RuntimeException::new);
        //then
        assertThat(save.getImageId()).as("imageId 값이 할당되어야한다").isNotNull();
        assertThat(find.getImageUrl()).as("다른 필드들이 정상적으로 저장되었는지를 검증한다").isEqualTo(image.getImageUrl());
    }

    @DisplayName("id 를 조회조건으로 엔티티를 조회한다")
    @Test
    public void findByID() {
        //given
        //when
        Image image = imageRepository.findByImageId(1L).orElseThrow();
        //then
        assertThat(image.getImageUrl()).as("").isNotEmpty();
    }

    @DisplayName("전체 데이터를 가져온다")
    @Test
    public void findAll() {
        //given
        //when
        List<Image> all = imageRepository.findAll();
        //then
        for (Image image : all) {
            System.out.println(image);
        }
    }
}