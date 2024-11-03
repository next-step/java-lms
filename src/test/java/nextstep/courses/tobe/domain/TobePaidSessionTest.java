package nextstep.courses.tobe.domain;

import nextstep.courses.MaxStudentCapacityException;
import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.tobe.NotMatchedInstructorException;
import nextstep.courses.tobe.PaymentStudentNsUserNotMatchException;
import nextstep.courses.tobe.RecruitmentClosedException;
import nextstep.payments.PaymentMismatchException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.domain.PaidSession.MAX_STUDENT_CAPACITY_MESSAGE;
import static nextstep.courses.domain.PaidSession.PAYMENT_MISMATCH_MESSAGE;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.courses.domain.ApprovedStatus.APPROVED;
import static nextstep.courses.domain.ApprovedStatus.DENIED;
import static nextstep.courses.tobe.domain.InstructorTest.IN1;
import static nextstep.courses.tobe.domain.InstructorTest.IN2;
import static nextstep.courses.tobe.domain.ProcessStatus.READY;
import static nextstep.courses.tobe.domain.RecruitmentStatus.CLOSED;
import static nextstep.courses.tobe.domain.RecruitmentStatus.OPEN;
import static nextstep.courses.domain.SelectedStatus.REJECTED;
import static nextstep.courses.domain.SelectedStatus.SELECTED;
import static nextstep.courses.tobe.domain.TobePaidSession.NOT_MATCHED_PAYMENT_STUDENT_NS_USER_MESSAGE;
import static nextstep.courses.tobe.domain.TobeSession.*;
import static nextstep.courses.tobe.domain.TobeCoverImageTest.*;
import static nextstep.users.domain.NsUserTest.*;
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
            List.of(new TobeCoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200)),
            IN1,
            READY, CLOSED,
            MAX_REGISTER_COUNT, SESSION_AMOUNT,
            1L, START, START);

    private TobePaidSession paidSession;
    private TobePaidSession exceedMaxRegisterCountPaidSession;
    private TobePaidSession closedPaidSession;
    private Payment payment1;
    private TobeStudent studentSelectedDenied1;
    private TobeStudent studentRejectApproved1;
    private TobeStudent studentSelectedApproved1;
    private TobeStudent studentRejectDenied1;
    private Payment payment2;
    private TobeStudent studentSelectedDenied2;
    private TobeStudent studentRejectApproved2;
    private TobeStudent studentSelectedApproved2;
    private TobeStudent studentRejectDenied2;
    private Payment payment3;
    private TobeStudent studentSelectedDenied3;
    private TobeStudent studentRejectApproved3;
    private TobeStudent studentSelectedApproved3;
    private TobeStudent studentRejectDenied3;
    private Payment notMatchedPayment;
    private List<TobeCoverImage> coverImages;
    private DateRange dateRange;
    private long courseId;

    @BeforeEach
    void setUp() {
        courseId = CourseTest.C1.getId();
        dateRange = new DateRange(START, END);
        coverImages = List.of(new TobeCoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200));

        paidSession = new TobePaidSession(1L,
                courseId, dateRange, coverImages, IN1,
                READY, OPEN,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, START, START);

        exceedMaxRegisterCountPaidSession = new TobePaidSession(1L,
                courseId, dateRange, coverImages, IN1,
                READY, OPEN,
                EXCEED_MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, START, START);

        closedPaidSession = new TobePaidSession(1L,
                courseId, dateRange, coverImages, IN1,
                READY, CLOSED,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, START, START);

        payment1 = new Payment("pay1", 1L, JAVAJIGI, SESSION_AMOUNT);
        studentSelectedDenied1 = new TobeStudent(paidSession, JAVAJIGI, SELECTED, DENIED, LocalDateTime.now());
        studentSelectedApproved1 = new TobeStudent(paidSession, JAVAJIGI, SELECTED, APPROVED, LocalDateTime.now());
        studentRejectApproved1 = new TobeStudent(paidSession, JAVAJIGI, REJECTED, APPROVED, LocalDateTime.now());
        studentRejectDenied1 = new TobeStudent(paidSession, JAVAJIGI, REJECTED, DENIED, LocalDateTime.now());

        payment2 = new Payment("pay2", 1L, SANJIGI, SESSION_AMOUNT);
        studentSelectedDenied2 = new TobeStudent(paidSession, SANJIGI, SELECTED, DENIED, LocalDateTime.now());
        studentSelectedApproved2 = new TobeStudent(paidSession, SANJIGI, SELECTED, APPROVED, LocalDateTime.now());
        studentRejectApproved2 = new TobeStudent(paidSession, SANJIGI, REJECTED, APPROVED, LocalDateTime.now());
        studentRejectDenied2 = new TobeStudent(paidSession, SANJIGI, REJECTED, DENIED, LocalDateTime.now());

        payment3 = new Payment("pay3", 1L, THIRDJIGI, SESSION_AMOUNT);
        studentSelectedDenied3 = new TobeStudent(paidSession, THIRDJIGI, SELECTED, DENIED, LocalDateTime.now());
        studentSelectedApproved3 = new TobeStudent(paidSession, THIRDJIGI, SELECTED, APPROVED, LocalDateTime.now());
        studentRejectApproved3 = new TobeStudent(paidSession, THIRDJIGI, REJECTED, APPROVED, LocalDateTime.now());
        studentRejectDenied3 = new TobeStudent(paidSession, THIRDJIGI, REJECTED, DENIED, LocalDateTime.now());

        notMatchedPayment = new Payment("notMatchedPay", 1L, JAVAJIGI, NOT_MATCHED_AMOUNT);


    }

    @Test
    void register_성공() {
        TobePaidSession actual = new TobePaidSession(1L,
                courseId, dateRange, coverImages,IN1,
                READY, OPEN,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, START, START);

        paidSession.register(payment1, studentSelectedDenied1);
        assertThat(actual).isNotEqualTo(paidSession);

        actual.register(payment1, studentSelectedDenied1);
        assertThat(actual).isEqualTo(paidSession);
    }

    @Test
    void register_강의_최대_수강인원_초과() {
        assertThatThrownBy(() -> {
            exceedMaxRegisterCountPaidSession.register(payment1, studentSelectedDenied1);
            exceedMaxRegisterCountPaidSession.register(payment2, studentSelectedDenied2);
            exceedMaxRegisterCountPaidSession.register(payment3, studentSelectedDenied3);
        }).isInstanceOf(MaxStudentCapacityException.class)
                .hasMessage(MAX_STUDENT_CAPACITY_MESSAGE);
    }

    @Test
    void register_결제한_금액과_수강료_불일치() {
        assertThatThrownBy(() -> {
            paidSession.register(notMatchedPayment, studentSelectedDenied1);
        }).isInstanceOf(PaymentMismatchException.class)
                .hasMessage(PAYMENT_MISMATCH_MESSAGE);
    }

    @Test
    void register_payingNsUser_studentNsUser_불일치() {
        assertThatThrownBy(() -> {
            paidSession.register(payment1, studentSelectedDenied2);
        }).isInstanceOf(PaymentStudentNsUserNotMatchException.class)
                .hasMessage(NOT_MATCHED_PAYMENT_STUDENT_NS_USER_MESSAGE);
    }

    @Test
    void register_CLOSED_실패() {
        assertThatThrownBy(() -> {
            closedPaidSession.register(payment1, studentSelectedDenied1);
        }).isInstanceOf(RecruitmentClosedException.class)
                .hasMessage(NOT_ALLOWED_REGISTER_TO_CLOSED_SESSION_MESSAGE);
    }

    @Test
    void approveAll() {
        paidSession.register(payment1, studentSelectedDenied1);
        paidSession.register(payment2, studentSelectedDenied2);
        paidSession.register(payment3, studentSelectedApproved3);
        paidSession.approveAll(IN1);

        TobePaidSession expected = new TobePaidSession(1L,
                courseId, dateRange, coverImages, IN1,
                READY, OPEN,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, START, START);
        expected.register(payment1, studentSelectedApproved1);
        expected.register(payment2, studentSelectedApproved2);
        expected.register(payment3, studentSelectedApproved3);

        assertThat(paidSession).isEqualTo(expected);
    }

    @Test
    void approveAll_등록된_강사가아니면_실패() {
        paidSession.register(payment1, studentSelectedDenied1);
        paidSession.register(payment2, studentSelectedDenied2);
        paidSession.register(payment3, studentSelectedDenied3);
        assertThatThrownBy(() -> {
            paidSession.approveAll(IN2);
        })
                .isInstanceOf(NotMatchedInstructorException.class)
                .hasMessage(NO_AUTH_INSTRUCTOR_TO_UPDATE_APPROVE_STATUS_MESSAGE);
    }

    @Test
    void deniedAll() {
        paidSession.register(payment1, studentRejectApproved1);
        paidSession.register(payment2, studentRejectDenied2);
        paidSession.register(payment3, studentRejectApproved3);
        paidSession.deniedAll(IN1);

        TobePaidSession expected = new TobePaidSession(1L,
                courseId, dateRange, coverImages, IN1,
                READY, OPEN,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, START, START);
        expected.register(payment1, studentRejectDenied1);
        expected.register(payment2, studentRejectDenied2);
        expected.register(payment3, studentRejectDenied3);

        assertThat(paidSession).isEqualTo(expected);
    }

    @Test
    void deniedAll_등록된_강사가_아니면_실패() {
        paidSession.register(payment1, studentRejectApproved1);
        paidSession.register(payment2, studentRejectApproved2);
        paidSession.register(payment3, studentRejectApproved3);

        assertThatThrownBy(() -> {
            paidSession.deniedAll(IN2);
        })
                .isInstanceOf(NotMatchedInstructorException.class)
                .hasMessage(NO_AUTH_INSTRUCTOR_TO_UPDATE_DENIED_STATUS_MESSAGE);
    }
}
