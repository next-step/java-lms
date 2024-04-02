package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {


    @DisplayName("생성 테스트")
    @Test
    void create() {
        LocalDate startDate = LocalDate.of(2024, 1, 20);
        LocalDate endDate = LocalDate.of(2024, 3, 20);
        Session session = new Session(startDate, endDate);
        Assertions.assertThat(session.startDate()).isEqualTo(startDate);
        Assertions.assertThat(session.endDate()).isEqualTo(endDate);
    }
}
