package nextstep.users.domain;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.SessionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class NsUserTest {
    public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다")
    @Test
    void paidLectureCanBeAppliedIfPaidAmountEqualsTuitionFee() {
        // given
        Session paidLecture = new Session(1L, "객체지향 프로그래밍", 1L, LocalDate.of(2024, 5, 2), LocalDate.of(2024, 5, 6), SessionType.PAID, 100, 100, 10000);
        Payment payment = new Payment("1", paidLecture.getId(), JAVAJIGI.getId(), 10000L);

        // when
        SANJIGI.registerSession(paidLecture, payment.getAmount());

        // then
        assertThat(SANJIGI.getSessions().size()).isEqualTo(1);
    }

    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다")
    @Test
    void CourseRegistrationIsPossibleWhenCourseStatusIsOpening() {
        // given
        Session paidLecture = new Session(1L, "객체지향 프로그래밍", 1L, LocalDate.of(2024, 5, 2), LocalDate.of(2024, 5, 6), SessionType.PAID, 100, 100, 10000);
        Payment payment = new Payment("1", paidLecture.getId(), JAVAJIGI.getId(), 10000L);
        paidLecture.changeSessionStatus(SessionStatus.CLOSED);

        // when&then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    SANJIGI.registerSession(paidLecture, payment.getAmount());
                }).withMessageMatching("강의 모집 상태가 아닙니다. 수강 신청이 불가능합니다.");


    }
}
