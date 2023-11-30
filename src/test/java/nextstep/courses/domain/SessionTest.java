package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    private final String defaultTitle = "TDD, 클린 코드 with Java";
    private final LocalDateTime defaultStartDate = LocalDateTime.of(2024, 1, 1, 0, 0);
    private final LocalDateTime defaultEndDate = LocalDateTime.of(2024, 12, 31, 0, 0);

    @Test
    @DisplayName("Session은 제목, 시작일, 종료일을 가진다.")
    void testSessionFields() {
        //when
        Session session = new FreeSession(defaultTitle, defaultStartDate, defaultEndDate);

        //then
        assertThat(session.getTitle()).isEqualTo(defaultTitle);
        assertThat(session.getStartDate()).isEqualTo(defaultStartDate);
        assertThat(session.getEndDate()).isEqualTo(defaultEndDate);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Session의 제목은 비어있으면, 예외가 발생한다.")
    void testSessionTitleIsNotBlank(String title) {
        //when, then
        assertThatThrownBy(() -> new FreeSession(title, defaultStartDate, defaultEndDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("title cannot be blank");
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Session의 시작일에 Null이 들어가면, 예외가 발생한다.")
    void testSessionStartDateIsNotNull(LocalDateTime startDate) {
        //when, then
        assertThatThrownBy(() -> new FreeSession(defaultTitle, startDate, defaultEndDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("date cannot be null");
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Session의 종료일에 Null이 들어가면, 예외가 발생한다.")
    void testSessionEndDateIsNotNull(LocalDateTime endDate) {
        //when, then
        assertThatThrownBy(() -> new FreeSession(defaultTitle, defaultStartDate, endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("date cannot be null");
    }

    @Test
    @DisplayName("Session의 시작일이 종료일보다 느리면, 예외가 발생한다.")
    void testSessionStartDateIsBeforeEndDate() {
        //given
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 2, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 1, 1, 23, 59);

        //when, then
        assertThatThrownBy(() -> new FreeSession(defaultTitle, startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("start date cannot be after end date");
    }

    @Test
    @DisplayName("Session이 처음 생성되면, Session의 status는 Ready 상태를 가진다.")
    void testInitSessionStatusIsReady() {
        //given
        final Session session = new FreeSession(defaultTitle, defaultStartDate, defaultEndDate);

        //when
        final SessionStatus status = session.getStatus();

        //then
        assertThat(status).isEqualTo(SessionStatus.READY);
    }

    @Test
    @DisplayName("Session이 처음 생성되면, Session의 최대 수강인원을 15명으로 지정한다. (따로 요구한 사항이 없으므로, 임의로 지정)")
    void testInitSessionMaxStudentLimitIs15() {
        //given
        final Session session = new FreeSession(defaultTitle, defaultStartDate, defaultEndDate);

        //when
        final int maxStudentLimit = session.getMaxStudentLimit();

        //then
        assertThat(maxStudentLimit).isEqualTo(15);
    }

    @Test
    @DisplayName("Session이 처음 생성되면, Session의 현재 수강인원을 0명으로 지정한다.")
    void testInitSessionCurrentStudentCountIs0() {
        //given
        final Session session = new FreeSession(defaultTitle, defaultStartDate, defaultEndDate);

        //when
        final int currentStudentCount = session.getCurrentStudentCount();

        //then
        assertThat(currentStudentCount).isEqualTo(0);
    }
}
