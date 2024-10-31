package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class FreeSessionTest {

    private FreeSession freeSession;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @BeforeEach
    void setUp() {
        String title = "자바의 정석";
        startDate = LocalDateTime.of(2024, 1, 1, 10, 0);
        endDate = LocalDateTime.of(2024, 1, 10, 18, 0);
        freeSession = new FreeSession(
                title,
                new SessionPeriod(startDate, endDate),
                CoverImage.of(500 * 1024, "jpg", 300, 200)
        );
    }

    @Test
    @DisplayName("무료 강의 생성 시 필드가 올바르게 설정되는지 확인")
    void createFreeSessionTest() {
        assertThat(freeSession.getTitle()).isEqualTo("자바의 정석");
        assertThat(freeSession.getPeriod().getStartDate()).isEqualTo(startDate);
        assertThat(freeSession.getPeriod().getEndDate()).isEqualTo(endDate);
        assertThat(freeSession.getCoverImage().getWidth()).isEqualTo(300);
        assertThat(freeSession.getCoverImage().getHeight()).isEqualTo(200);
    }

    @Test
    @DisplayName("무료 강의는 isPaid() 호출 시 false 반환한다.")
    void isPaidTest() {
        assertThat(freeSession.isPaid()).isFalse();
    }

    @Test
    @DisplayName("무료 강의는 모집중 상태일 때만 수강 신청 가능하다.")
    void freeSessionEnrollTest() {
        freeSession.openEnrollment();

        assertThatCode(() -> freeSession.enroll(NsUserTest.SANJIGI, null))
                .doesNotThrowAnyException();

        assertThat(freeSession.getEnrolledUsers()).contains(NsUserTest.SANJIGI);
    }

    @Test
    @DisplayName("무료 강의는 모집중 상태가 아니면 수강 신청 시 예외가 발생한다.")
    void throwExceptionWhenStatusIsNotOpen() {
        assertThatThrownBy(() -> freeSession.enroll(NsUserTest.JAVAJIGI, null))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모집중인 상태에서만 신청 가능합니다.");
    }
}
