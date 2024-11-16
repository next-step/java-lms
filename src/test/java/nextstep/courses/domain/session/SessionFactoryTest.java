package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.courses.domain.cover.ImageDimension;
import nextstep.courses.domain.cover.ImageExtension;
import nextstep.courses.domain.cover.ImageSize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class SessionFactoryTest {

    @DisplayName("무료 세션을 생성할 수 있다.")
    @Test
    void createFreeSession() {
        CoverImage coverImage = CoverImage.of("java", ImageSize.of(1000), ImageExtension.JPG.name(), ImageDimension.of(300, 200));
        SessionPeriod period = SessionPeriod.of(LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        SessionBody sessionBody = SessionBody.of("무료 세션", period, List.of(coverImage));
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(ProgressStatus.PREPARE, RecruitmentStatus.RECRUITING);

        Session session = SessionFactory.create(1L, 1L, sessionBody, sessionEnrollment, 0, 0);
        CoverImage retrievedCoverImage = SessionDomainTestHelper.getSingleCoverImage(session.getCoverImages());

        assertAll(
                () -> assertThat(session).isInstanceOf(FreeSession.class),
                () -> assertThat(session.getTitle()).isEqualTo(sessionBody.getTitle()),
                () -> assertThat(session.getStartDate()).isEqualTo(period.getStartDate()),
                () -> assertThat(session.getEndDate()).isEqualTo(period.getEndDate()),
                () -> assertThat(retrievedCoverImage.getFileName()).isEqualTo(coverImage.getFileName()),
                () -> assertThat(retrievedCoverImage.getHeight()).isEqualTo(coverImage.getHeight()),
                () -> assertThat(retrievedCoverImage.getWidth()).isEqualTo(coverImage.getWidth()),
                () -> assertThat(retrievedCoverImage.getImageSize()).isEqualTo(coverImage.getImageSize()),
                () -> assertThat(retrievedCoverImage.getExtension()).isEqualTo(coverImage.getExtension()),
                () -> assertThat(session.getFee()).isZero(),
                () -> assertThat(session.getMaxEnrollments()).isZero()
        );
    }

    @DisplayName("유료 세션을 생성할 수 있다.")
    @Test
    void createPaidSession() {
        CoverImage coverImage = CoverImage.of("python", ImageSize.of(1000), ImageExtension.JPG.name(), ImageDimension.of(300, 200));
        SessionPeriod period = SessionPeriod.of(LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        SessionBody sessionBody = SessionBody.of("유료 세션", period, List.of(coverImage));
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(ProgressStatus.PREPARE, RecruitmentStatus.RECRUITING);

        Session session = SessionFactory.create(1L, 1L, sessionBody, sessionEnrollment, 10000, 100);
        CoverImage retrievedCoverImage = SessionDomainTestHelper.getSingleCoverImage(session.getCoverImages());

        assertAll(
                () -> assertThat(session).isInstanceOf(PaidSession.class),
                () -> assertThat(session.getTitle()).isEqualTo(sessionBody.getTitle()),
                () -> assertThat(session.getStartDate()).isEqualTo(period.getStartDate()),
                () -> assertThat(session.getEndDate()).isEqualTo(period.getEndDate()),
                () -> assertThat(retrievedCoverImage.getFileName()).isEqualTo(coverImage.getFileName()),
                () -> assertThat(retrievedCoverImage.getHeight()).isEqualTo(coverImage.getHeight()),
                () -> assertThat(retrievedCoverImage.getWidth()).isEqualTo(coverImage.getWidth()),
                () -> assertThat(retrievedCoverImage.getImageSize()).isEqualTo(coverImage.getImageSize()),
                () -> assertThat(retrievedCoverImage.getExtension()).isEqualTo(coverImage.getExtension()),
                () -> assertThat(session.getFee()).isNotZero(),
                () -> assertThat(session.getMaxEnrollments()).isNotZero()
        );
    }
}
