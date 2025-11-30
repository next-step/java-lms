package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


class EnrollmentTest {

    private final CoverImage COVER_IMAGE = new CoverImage(1, "png", 300, 200);
    private final LocalDateTime START_DATE = LocalDateTime.of(2025, 11, 1, 0, 0, 0);
    private final LocalDateTime END_DATE = LocalDateTime.of(2025, 11, 30, 11, 59, 59);
    private final Session S1 = new Session(1L, START_DATE, END_DATE, "paid",
            100, 300_000L, "active", COVER_IMAGE);

    @Test
    void 수강신청_정상_생성() {
        Enrollment enrollment = new Enrollment(NsUserTest.JAVAJIGI, 1L, new Payment(1L, 1L, 300_000L));
        Assertions.assertThat(enrollment).isNotNull();
    }

}