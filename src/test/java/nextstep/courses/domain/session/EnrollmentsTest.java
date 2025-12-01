package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
class EnrollmentsTest {

    private final CoverImage COVER_IMAGE = new CoverImage(1, "png", 300, 200);
    private final LocalDateTime START_DATE = LocalDateTime.of(2025, 11, 1, 0, 0, 0);
    private final LocalDateTime END_DATE = LocalDateTime.of(2025, 11, 30, 11, 59, 59);

    @Test
    void 중복_수강신청시_예외발생() {
        Session session = new Session(1L, START_DATE, END_DATE, "paid",
                100, 300_000L, "active", COVER_IMAGE);
        Enrollment enrollment1 = new Enrollment(NsUserTest.JAVAJIGI, 1L, new Payment(1L, 1L, 300_000L));
        Enrollment enrollment2 = new Enrollment(NsUserTest.JAVAJIGI, 1L, new Payment(1L, 1L, 300_000L));

        session.addEnrollment(enrollment1);

        assertThatThrownBy(() -> session.addEnrollment(enrollment2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미 신청한 강의입니다.");
    }

}