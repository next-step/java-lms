package nextstep.courses.fixture.builder;

import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.cover.ImageHeight;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.courses.domain.cover.ImageType;
import nextstep.courses.domain.cover.ImageWidth;
import nextstep.courses.domain.student.ApprovalState;
import nextstep.courses.domain.student.Email;
import nextstep.courses.domain.student.Student;
import nextstep.courses.domain.student.StudentName;
import nextstep.courses.domain.student.StudentType;
import nextstep.payments.domain.Money;

public class StudentBuilder {

    private Long id;

    private StudentName studentName;

    private Email email;

    private Money paymentAmount;

    private ApprovalState approvalState;

    private StudentType studentType;

    private StudentBuilder() {}

    public static StudentBuilder anStudent() {
        return new StudentBuilder();
    }

    public StudentBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public StudentBuilder withStudentName(StudentName studentName) {
        this.studentName = studentName;
        return this;
    }

    public StudentBuilder withEmail(Email email) {
        this.email = email;
        return this;
    }

    public StudentBuilder withPaymentAmount(Money paymentAmount) {
        this.paymentAmount = paymentAmount;
        return this;
    }

    public StudentBuilder withApprovalState(ApprovalState approvalState) {
        this.approvalState = approvalState;
        return this;
    }

    public StudentBuilder withStudentType(StudentType studentType) {
        this.studentType = studentType;
        return this;
    }

    public Student build() {
        return new Student(id, studentName, email, paymentAmount, approvalState, studentType);
    }
}
