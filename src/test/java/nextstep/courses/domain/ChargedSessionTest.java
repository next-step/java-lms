package nextstep.courses.domain;

import nextstep.courses.DifferentSessionAmountException;
import nextstep.courses.ExceedMaxStudentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ChargedSessionTest {

    @Test
    @DisplayName("최대 수강 인원 초과 시 에러 발생한다")
    public void exceed_max_number_of_student() {
        ChargedSession chargedSession = ChargedSession.init(0L, duration(), image(), 0, BigDecimal.valueOf(10_000));

        assertThatExceptionOfType(ExceedMaxStudentException.class)
            .isThrownBy(() -> chargedSession.addStudent())
            .withMessageMatching("수강 인원을 초과했습니다.");
    }

    @Test
    @DisplayName("수강생이 결제한 금액과 수강료가 일치하지 않으면 수강 신청 시 에러 발생한다")
    public void validate_session_price() {
        ChargedSession chargedSession = ChargedSession.init(0L, duration(), image(), 5, BigDecimal.valueOf(10_000));

        assertThatExceptionOfType(DifferentSessionAmountException.class)
            .isThrownBy(() -> chargedSession.apply(1L, BigDecimal.valueOf(8_000)))
            .withMessageMatching("수강료와 결제 금액이 일치하지 않습니다.");
    }

    private Duration duration() {
        return new Duration(LocalDate.now(), LocalDate.now());
    }

    private Image image() {
        return new Image(1, "JPG", 300, 200);
    }
}
