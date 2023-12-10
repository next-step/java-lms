package nextstep.courses;

import nextstep.courses.domain.*;
import nextstep.courses.enumeration.SessionProgressType;
import nextstep.courses.enumeration.SessionRecruitStatus;
import nextstep.courses.enumeration.SessionStudentStatus;
import nextstep.courses.exception.CanNotRegisterSessionException;
import nextstep.courses.exception.ExceedStudentsCountException;
import nextstep.courses.exception.PaymentMisMatchException;
import nextstep.courses.exception.StudentAlreadyApplyException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    private Session sessionInReady;
    private Session freeSessionInProgress;
    private Session costMoneySessionInRegistering;
    private Payment paymentMatch;
    private Payment paymentMisMatch;
    private Payment freePayment;
    private SessionStudent javajigiStudent;
    private SessionStudent sanjigiStudent;

    @BeforeEach
    void setUp() {
        sessionInReady = FreeSession.of(1L,
                1L,
                "무료강의",
                SessionImages.of(List.of(SessionImage.of(1L, 1L,"url", "GIF", 1000L, 300L, 200L))),
                SessionRecruitStatus.NOT_RECRUITING,
                SessionProgressType.READY,
                LocalDateTime.of(2023, Month.DECEMBER, 3, 15, 0, 0),
                LocalDateTime.of(2023, Month.DECEMBER, 10, 15, 0, 0));

        freeSessionInProgress = FreeSession.of(1L,
                1L,
                "무료강의",
                SessionImages.of(List.of(SessionImage.of(1L, 1L, "url", "GIF", 1000L, 300L, 200L))),
                SessionRecruitStatus.NOT_RECRUITING,
                SessionProgressType.IN_PROGRESS,
                LocalDateTime.of(2023, Month.DECEMBER, 3, 15, 0, 0),
                LocalDateTime.of(2023, Month.DECEMBER, 10, 15, 0, 0));

        costMoneySessionInRegistering = CostMoneySession.of(1L,
                1L,
                "유료강의",
                SessionImages.of(List.of(SessionImage.of(1L, 1L, "url", "GIF", 1000L, 300L, 200L))),
                1,
                SessionRecruitStatus.RECRUITING,
                SessionProgressType.READY,
                2000,
                LocalDateTime.of(2023, Month.DECEMBER, 3, 15, 0, 0),
                LocalDateTime.of(2023, Month.DECEMBER, 10, 15, 0, 0));

        paymentMatch = Payment.of("1", 1L, 1L, 2000L);
        paymentMisMatch = Payment.of("1", 1L, 1L, 1000L);
        freePayment = Payment.of("1", 1L, 1L, 0L);
        javajigiStudent = SessionStudent.of(1L, 1L, JAVAJIGI.getId(), SessionStudentStatus.REQUESTED);
        sanjigiStudent = SessionStudent.of(1L, 1L, SANJIGI.getId(), SessionStudentStatus.REQUESTED);
    }

    @Test
    @DisplayName("강의의 시작일과 종료일이 없다면 예외가 발생한다.")
    void courseWithMultipleSessionTest() {
        assertThatThrownBy(() -> FreeSession.of(1L,
                1L,
                "무료강의",
                SessionImages.of(List.of(SessionImage.of(1L, 1L, "url", "GIF", 1000L, 300L, 200L))),
                SessionRecruitStatus.NOT_RECRUITING,
                SessionProgressType.READY,
                null,
                LocalDateTime.of(2023, Month.DECEMBER, 10, 15, 0, 0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의 시작일이 없습니다.");

        assertThatThrownBy(() -> CostMoneySession.of(1L,
                1L,
                "유료강의",
                SessionImages.of(List.of(SessionImage.of(1L, 1L, "url", "GIF", 1000L, 300L, 200L))),
                10,
                SessionRecruitStatus.NOT_RECRUITING,
                SessionProgressType.READY,
                2000,
                LocalDateTime.of(2023, Month.DECEMBER, 3, 12, 0, 0),
                null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의 종료일이 없습니다.");
    }

    @Test
    @DisplayName("무료강의는 인원제한 없이 신청할 수 있다.")
    void freeSessionTest() {
        freeSessionInProgress.register(javajigiStudent, freePayment);
        freeSessionInProgress.register(sanjigiStudent, freePayment);
        assertThat(freeSessionInProgress.getStudents().contains(javajigiStudent)).isTrue();
        assertThat(freeSessionInProgress.getStudents().contains(sanjigiStudent)).isTrue();
    }

    @Test
    @DisplayName("유료강의는 인원이 다 차면 더 이상 신청할 수 없다.")
    void costMoneySessionExceedStudentsTest() {
        costMoneySessionInRegistering.register(javajigiStudent, paymentMatch);

        assertThatThrownBy(() -> costMoneySessionInRegistering.register(sanjigiStudent, paymentMatch))
                .isInstanceOf(ExceedStudentsCountException.class)
                .hasMessage("정원이 초과되어 더 이상 신청할 수 없습니다.");
    }

    @Test
    @DisplayName("유료강의를 신청할 때 지불한 금액이 다르면 신청할 수 없다.")
    void paymentPriceMisMatchTest() {
        assertThatThrownBy(() -> costMoneySessionInRegistering.register(javajigiStudent, paymentMisMatch))
                .isInstanceOf(PaymentMisMatchException.class)
                .hasMessage("지불한 금액이 강의 가격과 일치하지 않습니다.");
    }

    @Test
    @DisplayName("유료강의를 신청할 때 지불한 금액이 일치하면 강의를 등록할 수 있다.")
    void paymentPriceMatchTest() {
        costMoneySessionInRegistering.register(javajigiStudent, paymentMatch);
        assertThat(costMoneySessionInRegistering.getStudents().contains(javajigiStudent)).isTrue();
    }

    @Test
    @DisplayName("강의가 모집 중 상태가 아니라면 신청이 불가능하다.")
    void sessionCanNotRegisterTest() {
        assertThatThrownBy(() -> sessionInReady.register(javajigiStudent, freePayment))
                .isInstanceOf(CanNotRegisterSessionException.class)
                .hasMessage("강의가 모집 중이거나 진행 중이여야만 신청할 수 있습니다.");
    }

    @Test
    @DisplayName("강의가 모집 중이거나 진행 중 상태라면 신청이 가능하다.")
    void sessionCanRegisterTest() {
        freeSessionInProgress.register(javajigiStudent, freePayment);
        assertThat(freeSessionInProgress.getStudents().contains(javajigiStudent)).isTrue();

        costMoneySessionInRegistering.register(sanjigiStudent, paymentMatch);
        assertThat(costMoneySessionInRegistering.getStudents().contains(sanjigiStudent)).isTrue();
    }

    @Test
    @DisplayName("이미 신청한 학생은 다시 신청할 수 없다.")
    void alreadyRegisterStudentTest() {
        freeSessionInProgress.register(javajigiStudent, freePayment);

        assertThatThrownBy(() -> freeSessionInProgress.register(javajigiStudent, freePayment))
                .isInstanceOf(StudentAlreadyApplyException.class)
                .hasMessage("이미 신청완료한 학생입니다.");
    }

    @Test
    @DisplayName("한 강의는 여러개의 이미지를 가질 수 있다.")
    void multipleSessionImagesTest() {
        SessionImage sessionImageOne = SessionImage.of(1L, 1L,"url1", "gif", 1024L, 300L, 200L);
        SessionImage sessionImageTwo = SessionImage.of(2L, 1L,"url2", "jpg", 1024L, 300L, 200L);
        SessionImages sessionImages = SessionImages.of(List.of(sessionImageOne, sessionImageTwo));

        Session sessionWithMultipleImages = FreeSession.of(1L,
                1L,
                "무료강의",
                sessionImages,
                SessionRecruitStatus.NOT_RECRUITING,
                SessionProgressType.READY,
                LocalDateTime.of(2023, Month.DECEMBER, 3, 15, 0, 0),
                LocalDateTime.of(2023, Month.DECEMBER, 10, 15, 0, 0));

        assertThat(sessionWithMultipleImages.getSessionImages()).size().isEqualTo(2);
    }

    @Test
    @DisplayName("강의 신청자를 승인할 수 있다.")
    void sessionStudentApproveTest() {
        javajigiStudent.approve();
        assertThat(javajigiStudent.getSessionStudentStatus()).isEqualTo(SessionStudentStatus.APPROVED);
    }

    @Test
    @DisplayName("강의 신청자를 취소할 수 있다.")
    void sessionStudentCanceledTest() {
        sanjigiStudent.canceled();
        assertThat(sanjigiStudent.getSessionStudentStatus()).isEqualTo(SessionStudentStatus.CANCELED);
    }
 }
