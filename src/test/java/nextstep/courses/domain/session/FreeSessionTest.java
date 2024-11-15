package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.courses.domain.cover.ImageDimension;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class FreeSessionTest {

    private SessionBody sessionBody;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @BeforeEach
    void setUp() {
        String title = "자바의 정석";
        startDate = LocalDateTime.of(2024, 1, 1, 10, 0);
        endDate = LocalDateTime.of(2024, 1, 10, 18, 0);
        ImageSize imageSize = ImageSize.of(500 * 1024);
        ImageDimension imageDimension = ImageDimension.of(300, 200);

        sessionBody = SessionBody.of(title, SessionPeriod.of(startDate, endDate), CoverImage.of("learning java", imageSize, "jpg", imageDimension));
    }

    @Test
    @DisplayName("무료 강의 생성 시 필드가 올바르게 설정되는지 확인")
    void createFreeSessionTest() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(ProgressStatus.IN_PROGRESS, RecruitmentStatus.RECRUITING);
        FreeSession freeSession = new FreeSession(1L, 1L, sessionBody, sessionEnrollment);

        assertAll(
                () -> assertThat("자바의 정석").isEqualTo(freeSession.getTitle()),
                () -> assertThat(startDate).isEqualTo(freeSession.getPeriod().getStartDate()),
                () -> assertThat(endDate).isEqualTo(freeSession.getPeriod().getEndDate()),
                () -> assertThat(300).isEqualTo(freeSession.getCoverImage().getWidth()),
                () -> assertThat(200).isEqualTo(freeSession.getCoverImage().getHeight())
        );
    }

    @Test
    @DisplayName("무료 강의는 모집중 또는 진행중인 상태일 때만 수강 신청 가능하다.")
    void freeSessionEnrollTest() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(ProgressStatus.IN_PROGRESS, RecruitmentStatus.RECRUITING);
        FreeSession freeSession = new FreeSession(1L, 1L, sessionBody, sessionEnrollment);

        assertThatCode(() -> freeSession.enroll(NsUserTest.SANJIGI, null))
                .doesNotThrowAnyException();

        assertThat(freeSession.getEnrolledUsers()).contains(NsUserTest.SANJIGI);
    }

    @Test
    @DisplayName("무료 강의는 진행중 또는 모집중 상태가 아니면 수강 신청 시 예외가 발생한다.")
    void throwExceptionWhenStatusIsNotInProgress() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(ProgressStatus.CLOSED, RecruitmentStatus.NOT_RECRUITING);
        FreeSession freeSession = new FreeSession(1L, 1L, sessionBody, sessionEnrollment);

        assertThatThrownBy(() -> freeSession.enroll(NsUserTest.JAVAJIGI, null))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("진행중 또는 모집중인 상태에서만 신청 가능합니다.");
    }
}
