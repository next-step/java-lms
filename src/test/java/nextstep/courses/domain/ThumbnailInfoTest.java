package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ThumbnailInfoTest {

    private ThumbnailInfo thumbnailInfo;

    @BeforeEach
    void setup() throws Exception{
        thumbnailInfo = new ThumbnailInfo();
    }

    @Test
    @DisplayName("썸네일 객체 생성 테스트 case 1 - 이미지 path 정보")
    void test_thumbnail_has_url(){
        assertThat(thumbnailInfo)
                .hasFieldOrProperty("url");
    }
}
