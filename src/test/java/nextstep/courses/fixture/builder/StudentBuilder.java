package nextstep.courses.fixture.builder;

import nextstep.courses.domain.student.ApprovalState;
import nextstep.courses.domain.student.Email;
import nextstep.courses.domain.student.Student;
import nextstep.courses.domain.student.StudentName;
import nextstep.courses.domain.student.StudentType;

public class StudentBuilder {

    private Long id;

    private StudentName studentName;

    private Email email;

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

    public StudentBuilder withApprovalState(ApprovalState approvalState) {
        this.approvalState = approvalState;
        return this;
    }

    public StudentBuilder withStudentType(StudentType studentType) {
        this.studentType = studentType;
        return this;
    }

    public Student build() {
        return new Student(id, studentName, email, approvalState, studentType);
    }
}
