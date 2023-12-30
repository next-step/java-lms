package nextstep.courses.domain;

import static nextstep.courses.domain.CoverImageBuilder.aCoverImage;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoverImageBuilderTest {
    @Test
    @DisplayName("name을 지정하여 CoverImage를 생성")
    void withName() {
        CoverImageBuilder builder = aCoverImage().withName("pobi.png");
        CoverImage coverImage = builder.build();
        assertThat(coverImage).isInstanceOf(CoverImage.class);
    }

    @Test
    @DisplayName("byteSize를 지정하여 CoverImage를 생성")
    void withByteSize() {
        CoverImageBuilder builder = aCoverImage().withByteSize(500L);
        CoverImage coverImage = builder.build();
        assertThat(coverImage).isInstanceOf(CoverImage.class);
    }

    @Test
    @DisplayName("width를 지정하여 CoverImage를 생성")
    void withWidth() {
        CoverImageBuilder builder = aCoverImage().withWidth(300D);
        CoverImage coverImage = builder.build();
        assertThat(coverImage).isInstanceOf(CoverImage.class);
    }

    @Test
    @DisplayName("height를 지정하여 CoverImage를 생성")
    void withHeight() {
        CoverImageBuilder builder = aCoverImage().withHeight(200D);
        CoverImage coverImage = builder.build();
        assertThat(coverImage).isInstanceOf(CoverImage.class);
    }
}
