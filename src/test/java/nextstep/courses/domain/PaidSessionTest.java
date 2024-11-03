package nextstep.courses.domain;

import nextstep.courses.MaxStudentCapacityException;
import nextstep.courses.NotMatchedInstructorException;
import nextstep.courses.PaymentStudentNsUserNotMatchException;
import nextstep.courses.RecruitmentClosedException;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import nextstep.payments.PaymentMismatchException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.domain.ApprovedStatus.APPROVED;
import static nextstep.courses.domain.ApprovedStatus.DENIED;
import static nextstep.courses.domain.CoverImageTest.*;
import static nextstep.courses.domain.InstructorTest.IN1;
import static nextstep.courses.domain.InstructorTest.IN2;
import static nextstep.courses.domain.PaidSession.*;
import static nextstep.courses.domain.ProcessStatus.READY;
import static nextstep.courses.domain.RecruitmentStatus.CLOSED;
import static nextstep.courses.domain.RecruitmentStatus.OPEN;
import static nextstep.courses.domain.SelectedStatus.REJECTED;
import static nextstep.courses.domain.SelectedStatus.SELECTED;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaidSessionTest {

    public static final int MAX_REGISTER_COUNT = 30;
    public static final int EXCEED_MAX_REGISTER_COUNT = 2;
    public static final long SESSION_AMOUNT = 10000L;
    public static final long NOT_MATCHED_AMOUNT = 9999L;

    private long sessionId;
    private PaidSession paidSession;
    private PaidSession exceedMaxRegisterCountPaidSession;
    private PaidSession closedPaidSession;
    private Payment payment1;
    private Student studentSelectedDenied1;
    private Student studentRejectApproved1;
    private Student studentSelectedApproved1;
    private Student studentRejectDenied1;
    private Payment payment2;
    private Student studentSelectedDenied2;
    private Student studentRejectApproved2;
    private Student studentSelectedApproved2;
    private Student studentRejectDenied2;
    private Payment payment3;
    private Student studentSelectedDenied3;
    private Student studentRejectApproved3;
    private Student studentSelectedApproved3;
    private Student studentRejectDenied3;
    private Payment notMatchedPayment;
    private List<CoverImage> coverImages;
    private DateRange dateRange;
    private long courseId;
    private CoverImage coverImage;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @BeforeEach
    void setUp() {
        sessionId = 1L;
        coverImage = new CoverImage(sessionId, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200);
        status = Status.PREPARE;
        courseId = CourseTest.C1.getId();
        dateRange = new DateRange(START, END);
        coverImages = List.of(new CoverImage(sessionId, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200));
        createdAt = LocalDateTime.of(2024, 10, 26, 10, 0);
        updatedAt = LocalDateTime.of(2024, 11, 26, 10, 0);

        paidSession = new PaidSession(sessionId,
                courseId, dateRange,
                coverImage, status,
                coverImages, IN1, READY, OPEN,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, createdAt, updatedAt);

        exceedMaxRegisterCountPaidSession = new PaidSession(sessionId,
                courseId, dateRange,
                coverImage, status,
                coverImages, IN1, READY, OPEN,
                EXCEED_MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, createdAt, updatedAt);

        closedPaidSession = new PaidSession(sessionId,
                courseId, dateRange, coverImage, status,
                coverImages, IN1, READY, CLOSED,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, createdAt, updatedAt);

        payment1 = new Payment("pay1", sessionId, JAVAJIGI, SESSION_AMOUNT);
        studentSelectedDenied1 = new Student(paidSession, JAVAJIGI, SELECTED, DENIED, LocalDateTime.now());
        studentSelectedApproved1 = new Student(paidSession, JAVAJIGI, SELECTED, APPROVED, LocalDateTime.now());
        studentRejectApproved1 = new Student(paidSession, JAVAJIGI, REJECTED, APPROVED, LocalDateTime.now());
        studentRejectDenied1 = new Student(paidSession, JAVAJIGI, REJECTED, DENIED, LocalDateTime.now());

        payment2 = new Payment("pay2", sessionId, SANJIGI, SESSION_AMOUNT);
        studentSelectedDenied2 = new Student(paidSession, SANJIGI, SELECTED, DENIED, LocalDateTime.now());
        studentSelectedApproved2 = new Student(paidSession, SANJIGI, SELECTED, APPROVED, LocalDateTime.now());
        studentRejectApproved2 = new Student(paidSession, SANJIGI, REJECTED, APPROVED, LocalDateTime.now());
        studentRejectDenied2 = new Student(paidSession, SANJIGI, REJECTED, DENIED, LocalDateTime.now());

        payment3 = new Payment("pay3", sessionId, THIRDJIGI, SESSION_AMOUNT);
        studentSelectedDenied3 = new Student(paidSession, THIRDJIGI, SELECTED, DENIED, LocalDateTime.now());
        studentSelectedApproved3 = new Student(paidSession, THIRDJIGI, SELECTED, APPROVED, LocalDateTime.now());
        studentRejectApproved3 = new Student(paidSession, THIRDJIGI, REJECTED, APPROVED, LocalDateTime.now());
        studentRejectDenied3 = new Student(paidSession, THIRDJIGI, REJECTED, DENIED, LocalDateTime.now());

        notMatchedPayment = new Payment("notMatchedPay", 1L, JAVAJIGI, NOT_MATCHED_AMOUNT);


    }

    @Test
    void register_성공() {
        PaidSession actual = new PaidSession(sessionId,
                courseId, dateRange, coverImage, status,
                coverImages, IN1, READY, OPEN,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, createdAt, updatedAt);

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

        PaidSession expected = new PaidSession(1L,
                courseId, dateRange, coverImage, status,
                coverImages, IN1, READY, OPEN,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, createdAt, updatedAt);
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

        PaidSession expected = new PaidSession(1L,
                courseId, dateRange, coverImage, status,
                coverImages, IN1, READY, OPEN,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, createdAt, updatedAt);
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
