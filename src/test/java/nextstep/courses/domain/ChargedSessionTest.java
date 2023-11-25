package nextstep.courses.domain;

import nextstep.courses.ExceedMaxStudentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ChargedSessionTest {

    @Test
    @DisplayName("최대 수강 인원 초과 시 에러 발생한다")
    public void exceed_max_number_of_student() {
        Duration duration = new Duration(LocalDate.now(), LocalDate.now());
        Image image = new Image(1, "JPG", 300, 200);
        ChargedSession chargedSession = ChargedSession.init(duration, image, 0);

        assertThatExceptionOfType(ExceedMaxStudentException.class)
            .isThrownBy(() -> chargedSession.addStudent())
            .withMessageMatching("수강 인원을 초과했습니다.");
    }

}
