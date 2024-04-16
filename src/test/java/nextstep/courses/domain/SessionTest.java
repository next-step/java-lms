package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class SessionTest {
    @Test
    @DisplayName("등록 검증.")
    void check_enroll() {
        SessionTime sessionTime = new SessionTime(LocalDateTime.of(2024, 4, 13, 12, 5), LocalDateTime.of(2024, 4, 15, 2, 5));
        SessionType sessionType = SessionType.PAID;
        SessionStatus sessionStatus = SessionStatus.RECRUITING;
        SessionStudentCount sessionStudentCount = new SessionStudentCount(3, 5);
        Long fee = 500L;
        Image image = new Image(100, ImageType.JPEG, 300, 200);

        Session session = new Session(sessionTime, sessionType, sessionStatus, image, sessionStudentCount, fee);
        Payment payment = new Payment("cy", 20L, 20L, 400L);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            session.enroll(payment);
        });
    }

}
