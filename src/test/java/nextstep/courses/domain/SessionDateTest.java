package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SessionDateTest {
    public static SessionDate of() {
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(20);

        return new SessionDate(startDate, endDate);
    }


    @Test
    @DisplayName("강의 시작일, 종료일 생성 테스트")
    public void testMakeSessionDate() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(20);

        SessionDate sessionDate = new SessionDate(startDate, endDate);

        assertThat(sessionDate.getStartDate()).isEqualTo(startDate).isBefore(endDate).isAfterOrEqualTo(LocalDate.now());
        assertThat(sessionDate.getEndDate()).isEqualTo(endDate).isAfter(startDate);
    }

    @Test
    @DisplayName("강의 시작일이 현재 날짜 이전인 경우 에러 발생")
    public void testBeforeNow() {
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(20);

        assertThatIllegalArgumentException().isThrownBy(() -> new SessionDate(startDate, endDate));
    }

    @Test
    @DisplayName("강의 시작일이 종료일 이후인 경우 에러 발생")
    public void testEndDateAfterStartDate() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().minusDays(1);

        assertThatIllegalArgumentException().isThrownBy(() -> new SessionDate(startDate, endDate));
    }


}
