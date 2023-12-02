package nextstep.courses;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.SessionImages;
import nextstep.courses.enumeration.ExtensionType;
import nextstep.courses.enumeration.SessionStatus;
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
    private Session freeSession;
    private Session costMoneySession;
    private Payment paymentMatch;
    private Payment paymentMisMatch;

    @BeforeEach
    void setUp() {
        sessionInReady = Session.ofFree(1L,
                "무료강의",
                new SessionImages(List.of(new SessionImage(1L, "url", ExtensionType.GIF, 1000L, 300L, 200L))),
                0,
                SessionStatus.READY,
                LocalDateTime.of(2023, Month.DECEMBER, 3, 15, 0, 0),
                LocalDateTime.of(2023, Month.DECEMBER, 10, 15, 0, 0));

        freeSession = Session.ofFree(1L,
                "무료강의",
                new SessionImages(List.of(new SessionImage(1L, "url", ExtensionType.GIF, 1000L, 300L, 200L))),
                0,
                SessionStatus.REGISTERING,
                LocalDateTime.of(2023, Month.DECEMBER, 3, 15, 0, 0),
                LocalDateTime.of(2023, Month.DECEMBER, 10, 15, 0, 0));

        costMoneySession = Session.ofCostMoney(1L,
                "유료강의",
                new SessionImages(List.of(new SessionImage(1L, "url", ExtensionType.GIF, 1000L, 300L, 200L))),
                1,
                SessionStatus.REGISTERING,
                2000,
                LocalDateTime.of(2023, Month.DECEMBER, 3, 15, 0, 0),
                LocalDateTime.of(2023, Month.DECEMBER, 10, 15, 0, 0));

        paymentMatch = Payment.of("1", 1L, 1L, 2000L);
        paymentMisMatch = Payment.of("1", 1L, 1L, 1000L);
    }

    @Test
    @DisplayName("강의의 시작일과 종료일이 없다면 예외가 발생한다.")
    void courseWithMultipleSessionTest() {
        assertThatThrownBy(() -> Session.ofFree(1L,
                "무료강의",
                new SessionImages(List.of(new SessionImage(1L, "url", ExtensionType.GIF, 1000L, 300L, 200L))),
                0,
                SessionStatus.READY,
                null,
                LocalDateTime.of(2023, Month.DECEMBER, 10, 15, 0, 0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의 시작일이 없습니다.");

        assertThatThrownBy(() -> Session.ofCostMoney(1L,
                "유료강의",
                new SessionImages(List.of(new SessionImage(1L, "url", ExtensionType.GIF, 1000L, 300L, 200L))),
                10,
                SessionStatus.READY,
                2000,
                LocalDateTime.of(2023, Month.DECEMBER, 3, 12, 0, 0),
                null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의 종료일이 없습니다.");
    }

    @Test
    @DisplayName("무료강의는 인원제한 없이 등록할 수 있다.")
    void freeSessionTest() {
        freeSession.registerFreeSession(JAVAJIGI);
        freeSession.registerFreeSession(SANJIGI);
        assertThat(freeSession.getStudents().contains(JAVAJIGI)).isTrue();
        assertThat(freeSession.getStudents().contains(SANJIGI)).isTrue();
    }

    @Test
    @DisplayName("유료강의는 인원이 다 차면 더 이상 신청할 수 없다.")
    void costMoneySessionExceedStudentsTest() {
        costMoneySession.registerCostMoneySession(JAVAJIGI, paymentMatch);

        assertThatThrownBy(() -> costMoneySession.registerCostMoneySession(SANJIGI, paymentMatch))
                .isInstanceOf(ExceedStudentsCountException.class)
                .hasMessage("정원이 초과되어 더 이상 신청할 수 없습니다.");
    }

    @Test
    @DisplayName("유료강의를 신청할 때 지불한 금액이 다르면 신청할 수 없다.")
    void paymentPriceMisMatchTest() {
        assertThatThrownBy(() -> costMoneySession.registerCostMoneySession(JAVAJIGI, paymentMisMatch))
                .isInstanceOf(PaymentMisMatchException.class)
                .hasMessage("지불한 금액이 강의 가격과 일치하지 않습니다.");
    }

    @Test
    @DisplayName("유료강의를 신청할 때 지불한 금액이 일치하면 강의를 등록할 수 있다.")
    void paymentPriceMatchTest() {
        costMoneySession.registerCostMoneySession(JAVAJIGI, paymentMatch);
        assertThat(costMoneySession.getStudents().contains(JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("강의가 모집 중 상태가 아니라면 등록이 불가능하다.")
    void sessionCanNotRegisterTest() {
        assertThatThrownBy(() -> sessionInReady.registerFreeSession(JAVAJIGI))
                .isInstanceOf(CanNotRegisterSessionException.class)
                .hasMessage("강의가 모집중이여야만 신청할 수 있습니다.");
    }

    @Test
    @DisplayName("이미 등록한 학생은 다시 등록할 수 없다.")
    void alreadyRegisterStudentTest() {
        freeSession.registerFreeSession(JAVAJIGI);

        assertThatThrownBy(() -> freeSession.registerFreeSession(JAVAJIGI))
                .isInstanceOf(StudentAlreadyApplyException.class)
                .hasMessage("이미 신청완료한 학생입니다.");
    }
}
