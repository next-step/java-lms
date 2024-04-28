package nextstep.courses.domain.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.error.exception.AlreadyApprovedCancelException;
import nextstep.courses.error.exception.AlreadyApprovedException;
import nextstep.courses.error.exception.ApprovalNotAllowedException;
import nextstep.courses.fixture.builder.StudentBuilder;
import nextstep.payments.domain.Money;
import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void 학생유형이_우아한테크캠프_PRO_이면_승인한다() {
        Student student = StudentBuilder.anStudent()
            .withStudentName(new StudentName("김남협"))
            .withEmail(new Email("namhyeop@gmail.com"))
            .withPaymentAmount(new Money(50000))
            .withApprovalState(ApprovalState.NON_APPROVAL)
            .withStudentType(StudentType.WOOWAHAN_TECH_CAMP_PRO)
            .build();

        Student approvedStudent = student.approveStudent();

        assertThat(approvedStudent)
            .extracting("studentName", "email", "paymentAmount", "approvalState", "studentType")
            .containsExactly("김남협", "namhyeop@gmail.com", 50000, "승인",
                "우아한테크캠프 Pro(유료)");
    }

    @Test
    void 학생유형이_우아한테크코스_이면_승인한다() {
        Student student = StudentBuilder.anStudent()
            .withStudentName(new StudentName("김남협"))
            .withEmail(new Email("namhyeop@gmail.com"))
            .withPaymentAmount(new Money(50000))
            .withApprovalState(ApprovalState.NON_APPROVAL)
            .withStudentType(StudentType.WOOWAHAN_TECH_COURSE_FREE)
            .build();

        Student approvedStudent = student.approveStudent();

        assertThat(approvedStudent)
            .extracting("studentName", "email", "paymentAmount", "approvalState", "studentType")
            .containsExactly("김남협", "namhyeop@gmail.com", 50000, "승인", "우아한테크코스(무료)");
    }

    @Test
    void 이미_승인된_상태에서_승인하려할_경우_예외가_발생한다() {
        Student student = StudentBuilder.anStudent()
            .withStudentName(new StudentName("김남협"))
            .withEmail(new Email("namhyeop@gmail.com"))
            .withPaymentAmount(new Money(50000))
            .withApprovalState(ApprovalState.APPROVAL)
            .withStudentType(StudentType.WOOWAHAN_TECH_COURSE_FREE)
            .build();

        assertThatThrownBy(() -> student.approveStudent())
            .isInstanceOf(AlreadyApprovedException.class)
            .hasMessage("이미 수강신청이 승인된 학생입니다. 현재 승인 상태: 승인");
    }

    @Test
    void 학생유형이_일반_이면_수강신청시_예외가_발생한다() {
        Student student = StudentBuilder.anStudent()
            .withStudentName(new StudentName("김남협"))
            .withEmail(new Email("namhyeop@gmail.com"))
            .withPaymentAmount(new Money(50000))
            .withApprovalState(ApprovalState.NON_APPROVAL)
            .withStudentType(StudentType.NORMAL)
            .build();

        assertThatThrownBy(() -> student.approveStudent())
            .isInstanceOf(ApprovalNotAllowedException.class)
            .hasMessage("승인은 학생 유형 타입이 우아한테크코스 또는 우아한테크캠프인 경우만 수강신청이 가능합니다, 현재 학생 유형: 일반");
    }

    @Test
    void 수강에_대한_승인을_취소할_수_있다() {
        Student student = StudentBuilder.anStudent()
            .withStudentName(new StudentName("김남협"))
            .withEmail(new Email("namhyeop@gmail.com"))
            .withPaymentAmount(new Money(50000))
            .withApprovalState(ApprovalState.APPROVAL)
            .withStudentType(StudentType.WOOWAHAN_TECH_CAMP_PRO)
            .build();

        Student approvedStudent = student.approveCancelStudent();

        assertThat(approvedStudent)
            .extracting("studentName", "email", "paymentAmount", "approvalState", "studentType")
            .containsExactly("김남협", "namhyeop@gmail.com", 50000, "미승인",
                "우아한테크캠프 Pro(유료)");
    }

    @Test
    void 승인상태가_취소_상태에서_재취소할_경우_예외가_발생한다() {
        Student student = StudentBuilder.anStudent()
            .withStudentName(new StudentName("김남협"))
            .withEmail(new Email("namhyeop@gmail.com"))
            .withPaymentAmount(new Money(50000))
            .withApprovalState(ApprovalState.NON_APPROVAL)
            .withStudentType(StudentType.NORMAL)
            .build();

        assertThatThrownBy(() -> student.approveCancelStudent())
            .isInstanceOf(AlreadyApprovedCancelException.class)
            .hasMessage("이미 수강신청이 취소된 학생입니다. 현재 승인 상태: 미승인");
    }
}
