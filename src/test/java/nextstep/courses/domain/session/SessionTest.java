package nextstep.courses.domain.session;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImageType;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SessionTest {
    private CoverImage coverImage;
    private Period period;

    @BeforeEach
    void setUp() {
        coverImage = new CoverImage(1_048_576L, ImageType.JPG, 300, 200);
        period = new Period(LocalDate.of(2025, 11, 3), LocalDate.of(2025, 12, 18));
    }

    @Test
    void 강의를_생성할_수_있다() {
        assertThatNoException().isThrownBy(() ->
            new Session(
                period,
                coverImage,
                SessionStatus.RECRUITING,
                new EnrollmentPolicy(SessionType.FREE, 15, 50000L)
            )
        );
    }

    @Test
    void 강의_상태가_모집중이_아니면_수강신청할_수_없다() {
        Session session = new Session(
            period,
            coverImage,
            SessionStatus.PREPARING,
            new EnrollmentPolicy(SessionType.FREE, 15, 50000L)
        );

        assertThatThrownBy(() -> session.enroll(JAVAJIGI))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 같은_사용자는_중복_수강신청할_수_없다() {
        Session session = new Session(
            period,
            coverImage,
            SessionStatus.RECRUITING,
            new EnrollmentPolicy(SessionType.FREE, 15, 50000L)
        );

        session.enroll(JAVAJIGI);

        assertThatThrownBy(() -> session.enroll(JAVAJIGI))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 무료_강의는_수강_인원_제한_없이_수강신청할_수_있다() {
        Session session = new Session(
            period,
            coverImage,
            SessionStatus.RECRUITING,
            new EnrollmentPolicy(SessionType.FREE, 15, 50000L)
        );

        assertThatNoException().isThrownBy(() -> {
            session.enroll(JAVAJIGI);
            session.enroll(SANJIGI);
        });
    }

    @Test
    void 유료_강의는_최대_수강_인원을_초과하면_수강신청할_수_없다() {
        Session session = new Session(
            period,
            coverImage,
            SessionStatus.RECRUITING,
            new EnrollmentPolicy(SessionType.PAID, 1, 50000L)
        );

        Payment payment = new Payment("1", 1L, 1L, 50000L);
        session.enroll(JAVAJIGI, payment);

        Payment payment2 = new Payment("2", 1L, 2L, 50000L);
        assertThatThrownBy(() -> session.enroll(SANJIGI, payment2))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 유료_강의는_결제_금액과_수강료가_일치하지_않으면_수강신청할_수_없다() {
        Session session = new Session(
            period,
            coverImage,
            SessionStatus.RECRUITING,
            new EnrollmentPolicy(SessionType.PAID, 1, 50000L)
        );

        Payment payment = new Payment("1", 1L, 1L, 40000L);

        assertThatThrownBy(() -> session.enroll(JAVAJIGI, payment))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 유료_강의는_결제_금액과_수강료가_일치하면_수강신청할_수_있다() {
        Session session = new Session(
            period,
            coverImage,
            SessionStatus.RECRUITING,
            new EnrollmentPolicy(SessionType.PAID, 1, 50000L)
        );

        Payment payment = new Payment("1", 1L, 1L, 50000L);

        assertThatNoException().isThrownBy(() -> session.enroll(JAVAJIGI, payment));
    }
}
