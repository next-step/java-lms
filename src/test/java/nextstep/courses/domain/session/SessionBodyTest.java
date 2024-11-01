package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.courses.domain.cover.ImageDimension;
import nextstep.courses.domain.cover.ImageExtension;
import nextstep.courses.domain.cover.ImageSize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SessionBodyTest {


    @DisplayName("Session의 주요 정보를 가진 SessionBody를 생성한다.")
    @Test
    void sessionBodyCreateTest() {
        String title = "이펙티브 자바";
        LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 1, 10, 18, 0);
        SessionPeriod sessionPeriod = SessionPeriod.of(startDate, endDate);
        int imageSize = 500 * 1024;
        int width = 300;
        int height = 200;
        CoverImage coverImage = CoverImage.of(ImageSize.of(imageSize), "jpg", ImageDimension.of(width, height));


        SessionBody sessionBody = SessionBody.of(title, sessionPeriod, coverImage);

        assertAll(
                () -> assertEquals(title, sessionBody.getTitle()),
                () -> assertEquals(ImageExtension.JPG, sessionBody.getCoverImage().getExtension()),
                () -> assertEquals(imageSize, sessionBody.getCoverImage().getImageSize()),
                () -> assertEquals(width, sessionBody.getCoverImage().getWidth()),
                () -> assertEquals(height, sessionBody.getCoverImage().getHeight()),
                () -> assertEquals(startDate, sessionBody.getPeriod().getStartDate()),
                () -> assertEquals(endDate, sessionBody.getPeriod().getEndDate())
        );
    }
}