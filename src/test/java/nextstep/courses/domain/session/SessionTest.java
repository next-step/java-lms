package nextstep.courses.domain.session;

import static nextstep.courses.domain.session.SessionStatus.OPEN;
import static nextstep.courses.domain.session.SessionStatus.PREPARING;
import static nextstep.courses.domain.session.image.ImageType.GIF;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.session.image.CoverImage;
import nextstep.courses.domain.session.image.Dimensions;
import nextstep.courses.domain.session.image.Height;
import nextstep.courses.domain.session.image.Size;
import nextstep.courses.domain.session.image.Width;
import nextstep.courses.domain.session.strategy.FreeSessionStrategy;
import nextstep.courses.domain.session.strategy.PaidSessionStrategy;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;

class SessionTest {

    private final Name name = new Name("java");

    private final Schedule schedule = new Schedule(
            LocalDate.of(2024, 4, 1),
            LocalDate.of(2024, 4, 30)
    );

    private final CoverImage coverImage = new CoverImage(
            1L,
            GIF,
            new Size(10000),
            new Dimensions(new Width(300), new Height(200))
    );

    private final Course course = new Course(
            1L,
            "backend",
            1L,
            LocalDateTime.of(2024, 1, 1, 0, 0),
            LocalDateTime.of(2024, 1, 1, 0, 0)
    );

    @Test
    @DisplayName("새로운 강의를 생성한다.")
    void Session() {
        final Session actualSession = new Session(
                1L,
                this.name,
                PREPARING,
                this.schedule,
                this.coverImage,
                new FreeSessionStrategy(),
                this.course,
                new EnrollmentCount(0)
        );
        final Session expectedSession = new Session(
                1L,
                this.name,
                PREPARING,
                this.schedule,
                this.coverImage,
                new FreeSessionStrategy(),
                this.course,
                new EnrollmentCount(0)
        );

        assertThat(actualSession).isEqualTo(expectedSession);
    }

    @Test
    @DisplayName("특정 강의에 대한 수강 신청을 한다.")
    void Enroll_Session() {
        final Session session = new Session(
                1L,
                this.name,
                OPEN,
                this.schedule,
                this.coverImage,
                new PaidSessionStrategy(new Money(10000), new EnrollmentCount(10)),
                this.course,
                new EnrollmentCount(0)
        );

        session.enroll(new Payment(10000));

        assertThat(session.currentEnrollmentCount())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("강의 상태가 “모집중”이 아닐 때, 수강 신청하는 경우 예외를 던진다.")
    void Enroll_NotOpeningSession_Exception() {
        final Session session = new Session(
                1L,
                this.name,
                PREPARING,
                this.schedule,
                this.coverImage,
                new FreeSessionStrategy(),
                this.course,
                new EnrollmentCount(0)
        );

        assertThatIllegalStateException()
                .isThrownBy(() -> session.enroll(new Payment(0)));
    }

    @Test
    @DisplayName("유료 강의의 수강 인원 제한을 초과하는 경우 예외를 던진다.")
    void Enroll_ExceedEnrollmentLimit_Exception() {
        final Session session = new Session(
                1L,
                this.name,
                OPEN,
                this.schedule,
                this.coverImage,
                new PaidSessionStrategy(new Money(10000), new EnrollmentCount(10)),
                this.course,
                new EnrollmentCount(10)
        );

        assertThatIllegalStateException()
                .isThrownBy(() -> session.enroll(new Payment(10000)));
    }

    @Test
    @DisplayName("유료 강의의 결제 금액과 수강료가 일치하지 않는 경우 예외를 던진다.")
    void Enroll_NotEqualFeeAndPayment_Exception() {
        final Session session = new Session(
                1L,
                this.name,
                OPEN,
                this.schedule,
                this.coverImage,
                new PaidSessionStrategy(new Money(10000), new EnrollmentCount(10)),
                this.course,
                new EnrollmentCount(0)
        );

        assertThatIllegalArgumentException()
                .isThrownBy(() -> session.enroll(new Payment(9000)));
    }
}
