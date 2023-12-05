package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SessionTest {
    @Test
    void success() {
        Session session = new Session(
                1L,
                new Image(1000, Image.Type.JPEG, 300, 200),
                LocalDate.now(),
                LocalDate.now(),
                Session.Type.FREE,
                100,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
