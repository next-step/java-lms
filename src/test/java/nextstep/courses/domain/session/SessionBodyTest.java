package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.courses.domain.cover.ImageDimension;
import nextstep.courses.domain.cover.ImageSize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
        CoverImage coverImage = CoverImage.of("effective java", ImageSize.of(imageSize), "jpg", ImageDimension.of(width, height));

        SessionBody sessionBody = SessionBody.of(title, sessionPeriod, List.of(coverImage));
        CoverImage retrievedCoverImage = SessionDomainTestHelper.getSingleCoverImage(sessionBody.getCoverImages());

        assertAll(
                () -> assertThat(sessionBody.getTitle()).isEqualTo(title),
                () -> assertThat(sessionBody.getPeriod().getStartDate()).isEqualTo(startDate),
                () -> assertThat(sessionBody.getPeriod().getEndDate()).isEqualTo(endDate)
        );

        assertThat(retrievedCoverImage)
                .usingRecursiveComparison()
                .isEqualTo(coverImage);
    }
}