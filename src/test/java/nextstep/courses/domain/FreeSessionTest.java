package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FreeSessionTest {
    @Test
    @DisplayName("FreeSession 생성")
    void testFreeSessionConstructor() {
        //given
        final String title = "TDD, 클린 코드 with Java";
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        //when
        FreeSession freeSession = new FreeSession(title, startDate, endDate);

        //then
        assertThat(freeSession.getTitle()).isEqualTo(title);
        assertThat(freeSession.getStartDate()).isEqualTo(startDate);
        assertThat(freeSession.getEndDate()).isEqualTo(endDate);
    }

    @Test
    @DisplayName("수강 신청시에 강의 상태가 모집중이 아니면, 예외가 발생한다. (강의가 처음 열리면 강의 상태는 READY이다.)")
    void testEnrollWithInitSession() {
        //given
        final Session freeSession = buildDefaultFreeSession();
        Payment payment = new Payment("tddJava", 0L, 0L, 0L);

        //when, then
        assertThatThrownBy(() -> freeSession.enroll(payment))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("session is not recruiting");
    }

    @Test
    @DisplayName("수강 신청시에 강의 상태가 모집중이 아니면, 예외가 발생한다. (READY 상태)")
    void testEnrollWithSessionStatusIsReady() {
        //given
        final Session freeSession = buildDefaultFreeSession();
        freeSession.ready();
        Payment payment = new Payment("tddJava", 0L, 0L, 0L);

        //when, then
        assertThatThrownBy(() -> freeSession.enroll(payment))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("session is not recruiting");
    }

    @Test
    @DisplayName("수강 신청시에 강의 상태가 모집중이 아니면, 예외가 발생한다. (CLOSED 상태)")
    void testEnrollWithSessionStatusIsClosed() {
        //given
        final Session freeSession = buildDefaultFreeSession();
        freeSession.close();
        Payment payment = new Payment("tddJava", 0L, 0L, 0L);

        //when, then
        assertThatThrownBy(() -> freeSession.enroll(payment))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("session is not recruiting");
    }

    @Test
    @DisplayName("강의 상태가 모집중이면, 수강 신청이 가능하다.")
    void testEnroll() {
        //given
        final Session freeSession = buildDefaultFreeSession();
        freeSession.recruit();
        Payment payment = new Payment("tddJava", 0L, 0L, 0L);

        final int currentStudentCountBeforeEnroll = freeSession.getCurrentStudentCount();

        //when
        freeSession.enroll(payment);
        final int currentStudentCountAfterEnroll = freeSession.getCurrentStudentCount();

        //then
        assertThat(currentStudentCountAfterEnroll).isEqualTo(currentStudentCountBeforeEnroll + 1);
    }

    private Session buildDefaultFreeSession() {
        final String title = "TDD, 클린 코드 with Java";
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        return new FreeSession(title, startDate, endDate);
    }
}
