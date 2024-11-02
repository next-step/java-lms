package nextstep.courses.tobe.domain;

import nextstep.courses.MaxStudentCapacityException;
import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.tobe.PaymentStudentNsUserNotMatchException;
import nextstep.courses.tobe.RecruitmentClosedException;
import nextstep.courses.tobe.domain.session.TobeCoverImage;
import nextstep.payments.PaymentMismatchException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.PaidSession.MAX_STUDENT_CAPACITY_MESSAGE;
import static nextstep.courses.domain.PaidSession.PAYMENT_MISMATCH_MESSAGE;
import static nextstep.courses.domain.session.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.courses.tobe.domain.ProcessStatus.READY;
import static nextstep.courses.tobe.domain.RecruitmentStatus.CLOSED;
import static nextstep.courses.tobe.domain.RecruitmentStatus.OPEN;
import static nextstep.courses.tobe.domain.TobePaidSession.NOT_MATCHED_PAYMENT_STUDENT_NS_USER_MESSAGE;
import static nextstep.courses.tobe.domain.TobeSession.NOT_ALLOWED_REGISTER_TO_CLOSED_SESSION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TobePaidSessionTest {

    public static final int MAX_REGISTER_COUNT = 30;
    public static final int EXCEED_MAX_REGISTER_COUNT = 2;
    public static final long SESSION_AMOUNT = 10000L;
    public static final long NOT_MATCHED_AMOUNT = 9999L;
    public static final TobePaidSession TPS1 = new TobePaidSession(1L,
            CourseTest.C1.getId(),
            new DateRange(START, END),
            new TobeCoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT, 1L),
            READY, CLOSED,
            MAX_REGISTER_COUNT, SESSION_AMOUNT,
            1L, START, START);

    private TobePaidSession paidSession;
    private TobePaidSession exceedMaxRegisterCountPaidSession;
    private TobePaidSession closedPaidSession;
    private Payment payment1;
    private TobeStudent student1;
    private Payment payment2;
    private TobeStudent student2;
    private Payment payment3;
    private TobeStudent student3;
    private Payment notMatchedPayment;

    @BeforeEach
    void setUp() {
        paidSession = new TobePaidSession(1L,
                CourseTest.C1.getId(),
                new DateRange(START, END),
                new TobeCoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT, 1L),
                READY, OPEN,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, START, START);

        exceedMaxRegisterCountPaidSession = new TobePaidSession(1L,
                CourseTest.C1.getId(),
                new DateRange(START, END),
                new TobeCoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT, 1L),
                READY, OPEN,
                EXCEED_MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, START, START);

        closedPaidSession = new TobePaidSession(1L,
                CourseTest.C1.getId(),
                new DateRange(START, END),
                new TobeCoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT, 1L),
                READY, CLOSED,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, START, START);

        payment1 = new Payment("pay1", 1L, NsUserTest.JAVAJIGI, SESSION_AMOUNT);
        student1 = new TobeStudent(paidSession, NsUserTest.JAVAJIGI, SelectedStatus.SELECTED, ApprovedStatus.APPROVED, LocalDateTime.now());
        payment2 = new Payment("pay2", 1L, NsUserTest.SANJIGI, SESSION_AMOUNT);
        student2 = new TobeStudent(paidSession, NsUserTest.SANJIGI, SelectedStatus.SELECTED, ApprovedStatus.APPROVED, LocalDateTime.now());
        payment3 = new Payment("pay3", 1L, NsUserTest.THIRDJIGI, SESSION_AMOUNT);
        student3 = new TobeStudent(paidSession, NsUserTest.THIRDJIGI, SelectedStatus.SELECTED, ApprovedStatus.APPROVED, LocalDateTime.now());

        notMatchedPayment = new Payment("pay4", 1L, NsUserTest.JAVAJIGI, NOT_MATCHED_AMOUNT);
    }

    @Test
    void register_성공() {
        TobePaidSession actual = new TobePaidSession(1L,
                CourseTest.C1.getId(),
                new DateRange(START, END),
                new TobeCoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT, 1L),
                READY, OPEN,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, START, START);

        paidSession.register(payment1, student1);
        assertThat(actual).isNotEqualTo(paidSession);

        actual.register(payment1, student1);
        assertThat(actual).isEqualTo(paidSession);
    }

    @Test
    void register_강의_최대_수강인원_초과() {
        assertThatThrownBy(() -> {
            exceedMaxRegisterCountPaidSession.register(payment1, student1);
            exceedMaxRegisterCountPaidSession.register(payment2, student2);
            exceedMaxRegisterCountPaidSession.register(payment3, student3);
        }).isInstanceOf(MaxStudentCapacityException.class)
                .hasMessage(MAX_STUDENT_CAPACITY_MESSAGE);
    }

    @Test
    void register_결제한_금액과_수강료_불일치() {
        assertThatThrownBy(() -> {
            paidSession.register(notMatchedPayment, student1);
        }).isInstanceOf(PaymentMismatchException.class)
                .hasMessage(PAYMENT_MISMATCH_MESSAGE);
    }

    @Test
    void register_payingNsUser_studentNsUser_불일치() {
        assertThatThrownBy(() -> {
            paidSession.register(payment1, student2);
        }).isInstanceOf(PaymentStudentNsUserNotMatchException.class)
                .hasMessage(NOT_MATCHED_PAYMENT_STUDENT_NS_USER_MESSAGE);
    }

    @Test
    void register_CLOSED_실패() {
        assertThatThrownBy(() -> {
            closedPaidSession.register(payment1, student1);
        }).isInstanceOf(RecruitmentClosedException.class)
                .hasMessage(NOT_ALLOWED_REGISTER_TO_CLOSED_SESSION_MESSAGE);
    }
}
