package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.ImageException;
import nextstep.courses.PeriodException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    private Image image;

    @BeforeEach
    void setUp() throws ImageException {
        image = Image.createImage(500_000, 300, 200, "png");
    }

    @Test
    void 강의_생성() throws PeriodException {
        Session session = new Session(1L, "자동차경주게임",
                LocalDate.of(2024, 4, 1),
                LocalDate.of(2024, 4, 30),
                image, ChargeType.CHARGE, 500_000,
                30, SessionStatus.ENROLLING);
        assertThat(session.enrolledStudents()).isEqualTo(0);
    }

    @Test
    void 강의_수강신청() throws CannotEnrollException, PeriodException {
        Session session = new Session(1L, "자동차경주게임",
                LocalDate.of(2024, 4, 1),
                LocalDate.of(2024, 4, 30),
                image, ChargeType.CHARGE, 500_000,
                30, SessionStatus.ENROLLING);
        session.enroll(NsUser.GUEST_USER, 500_000);
        assertThat(session.enrolledStudents()).isEqualTo(1);
    }

    @Test
    void 수강신청_최대수강인원_초과() throws CannotEnrollException, PeriodException {
        Session session = new Session(1L, "자동차경주게임",
                LocalDate.of(2024, 4, 1),
                LocalDate.of(2024, 4, 30),
                image, ChargeType.CHARGE, 500_000,
                1, SessionStatus.ENROLLING);
        session.enroll(NsUser.GUEST_USER, 500_000);

        assertThatThrownBy(() -> session.enroll(NsUser.GUEST_USER, 500_000))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessage("최대수강인원을 초과할 수 없습니다.");
    }

    @Test
    void 수강신청_결제금액이_수강료와_다른_경우() throws PeriodException {
        Session session = new Session(1L, "자동차경주게임",
                LocalDate.of(2024, 4, 1),
                LocalDate.of(2024, 4, 30),
                image, ChargeType.CHARGE, 500_000,
                1, SessionStatus.ENROLLING);

        assertThatThrownBy(() -> session.enroll(NsUser.GUEST_USER, 400_000))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessage("결제한 금액이 수강료와 다릅니다.");
    }

    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, mode = EnumSource.Mode.EXCLUDE, names = {"ENROLLING"})
    void 수강신청_강의상태가_모집중이_아닌_경우(SessionStatus sessionStatus) throws PeriodException {
        Session session = new Session(1L, "자동차경주게임",
                LocalDate.of(2024, 4, 1),
                LocalDate.of(2024, 4, 30),
                image, ChargeType.CHARGE, 500_000,
                1, sessionStatus);

        assertThatThrownBy(() -> session.enroll(NsUser.GUEST_USER, 500_000))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessage("모집 중인 강의가 아닙니다.");
    }

}
