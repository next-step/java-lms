package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class SessionServiceTest {
    private static final String IMAGE_PATH =
            System.getProperty("user.dir") + "/src/test/java/nextstep/courses/resources/";

    SessionService service;
    Session freeSession;
    Session freeSessionWithStudent;
    Session paidSession;

    Session paidSessionWithStudent;

    @BeforeEach
    void setUp() throws IOException {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();
        CoverImage coverImage = new CoverImage(IMAGE_PATH + "validCoverImage.png");

        service = new SessionService();

        freeSession = new FreeSession(startDate, endDate, coverImage, SessionStatus.ACCEPTING, 0);

        freeSessionWithStudent = new FreeSession(startDate, endDate, coverImage, SessionStatus.ACCEPTING, 1);

        paidSession = new PaidSession(startDate, endDate, coverImage, 10_000L, SessionStatus.ACCEPTING, 10, 0);

        paidSessionWithStudent = new PaidSession(
                startDate,
                endDate,
                coverImage,
                10_000L,
                SessionStatus.ACCEPTING,
                10,
                1
        );
    }

    @Test
    void register_무료_강의를_신청할_수_있다() {
        Payment payment = new Payment("payment-id", 1L, 2L, 0L);
        service.register(payment, freeSession);

        assertThat(freeSession).isEqualTo(freeSessionWithStudent);
    }

    @Test
    void register_유료_강의를_신청할_수_있다() {
        Payment payment = new Payment("payment-id", 1L, 2L, 10_000L);
        service.register(payment, paidSession);

        assertThat(paidSession).isEqualTo(paidSessionWithStudent);
    }
}
